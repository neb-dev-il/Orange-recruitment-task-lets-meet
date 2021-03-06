import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Person {

    private static final Scanner SCANNER = new Scanner(System.in);
    private LocalTime START_TIME;
    private LocalTime END_TIME;
    private List<LocalTime> MEETINGS_STARTS_AND_ENDS;

    void printCalendar(int calendarNumber, LocalTime start, LocalTime end, List<LocalTime> meetingsStartsAndEnds) {

        System.out.printf("\nCalendar %d\n", calendarNumber);
        System.out.print("{\n" +
                         "  working_hours: {\n" +
                         "    start: \"" + start + "\",\n" +
                         "    end: \"" + end + "\"\n" +
                         " },\n" +
                           "planned_meeting: [\n");

        for (int i = 1; i <= meetingsStartsAndEnds.size() - 1; i = i + 2) {

            System.out.print("    start: \"" + meetingsStartsAndEnds.get(i - 1) + "\",\n" +
                             "    end: \"" + meetingsStartsAndEnds.get(i) + "\"\n" +
                             " },\n");
        }

        System.out.print("]\n" +
                       "}\n");
    }

    void getStart() {

        System.out.print("Please, input start time in HH:MM format: ");

        while (true) {
            String start = SCANNER.next();
            try {
                this.START_TIME = LocalTime.parse(start, DateTimeFormatter.ofPattern("HH:mm"));
                break;
            } catch (DateTimeParseException e) {
                System.out.print("Please, input hour in HH:MM format: ");
            }

        }

    }

    void getEnd() {

        System.out.print("Please, input end time in HH:MM format: ");

        while (true) {
            String end = SCANNER.next();
            try {
                this.END_TIME = LocalTime.parse(end, DateTimeFormatter.ofPattern("HH:mm"));
                break;
            } catch (DateTimeParseException e) {
                System.out.print("Please, input hour in HH:MM format: ");
            }

        }

    }

    void getMeetingsStartsAndEnds() {

        List<LocalTime> list = new ArrayList<>();
        int counter = 1;

        System.out.print("Please, input hours for all meetings in HH:MM format. After, input STOP\n");

        while (true) {
            System.out.print(counter % 2 == 0 ? "end: " : "start: ");
            String hourOfMeeting = SCANNER.next();
            try {
                if (hourOfMeeting.equalsIgnoreCase("stop")) {
                    break;
                }
                list.add(LocalTime.parse(hourOfMeeting, DateTimeFormatter.ofPattern("HH:mm")));
                ++counter;
            } catch (DateTimeParseException e) {
                System.out.print("Please, input hour in HH:MM format: ");
            }

        }

        this.MEETINGS_STARTS_AND_ENDS = list;

    }

    LocalTime getSTART_TIME() {
        return START_TIME;
    }

    LocalTime getEND_TIME() {
        return END_TIME;
    }

    List<LocalTime> getMEETINGS_STARTS_AND_ENDS() {
        return MEETINGS_STARTS_AND_ENDS;
    }

    List<LocalTime> getHoursBetweenStartAndEnd(List<LocalTime> startsAndEnds, long meetingDuration) {

        List<LocalTime> hoursBetween = new ArrayList<>();
        int index = 0;

        for (int i = 0; i < startsAndEnds.size() - 1; i = i + 2) {

            hoursBetween.add(startsAndEnds.get(i));

            while (ChronoUnit.MINUTES.between(hoursBetween.get(index).plusMinutes(meetingDuration), startsAndEnds.get(i + 1)) >= meetingDuration) {
                LocalTime hour = hoursBetween.get(index).plusMinutes(meetingDuration);
                hoursBetween.add(hour);
                index++;
            }

            hoursBetween.remove(startsAndEnds.get(i));

        }

        return hoursBetween;

    }

    void deleteAllOccurrencesOfDuplicateHours(List<LocalTime> startsAndEnds) {

        List<LocalTime> duplicates = new ArrayList<>();

        for (int i = 1; i < startsAndEnds.size(); i++) {

            if (startsAndEnds.get(i).equals(startsAndEnds.get(i - 1))) {
                duplicates.add(startsAndEnds.get(i));
                duplicates.add(startsAndEnds.get(i - 1));
            }

        }

        this.MEETINGS_STARTS_AND_ENDS.removeAll(duplicates);

    }

}