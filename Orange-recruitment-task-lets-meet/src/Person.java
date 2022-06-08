import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Person {

    private static final Scanner SCANNER = new Scanner(System.in);
    private final LocalTime START_TIME;
    private final LocalTime END_TIME;
    private final List<LocalTime> startsAndEndsOfPlannedMeetings = new ArrayList<>();

    public Person(String startTime, String endTime, List<String> startsAndEndsOfPlannedMeetings) {

        this.START_TIME = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm"));
        this.END_TIME = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm"));

        for (String dateString : startsAndEndsOfPlannedMeetings) {
            this.startsAndEndsOfPlannedMeetings.add(LocalTime.parse(dateString, DateTimeFormatter.ofPattern("HH:mm")));
        }

    }

    static String getStart() {
        System.out.print("Please, input your start time of work (HH:MM): ");
        return SCANNER.next();
    }

    static String getEnd() {
        System.out.print("Please, input your end time of work (HH:MM): ");
        return SCANNER.next();
    }

    static List<String> getPlannedMeetingList() {

        List<String> list = new ArrayList<>();

        System.out.print("Please, input hours for all meetings in HH:MM format. After, input STOP\nstart: ");

        String hourOfMeeting = SCANNER.next();

        while (!hourOfMeeting.equalsIgnoreCase("stop")) {
            list.add(hourOfMeeting);
            System.out.print("end: ");
            hourOfMeeting = SCANNER.next();
            System.out.print("start: ");
            list.add(hourOfMeeting);
            hourOfMeeting = SCANNER.next();
        }

        return list;

    }

    LocalTime getSTART_TIME() {
        return START_TIME;
    }

    LocalTime getEND_TIME() {
        return END_TIME;
    }

    public List<LocalTime> getStartsAndEndsOfPlannedMeetings() {
        return startsAndEndsOfPlannedMeetings;
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

        this.startsAndEndsOfPlannedMeetings.removeAll(duplicates);

    }

}