import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.MINUTES;

class Timetable {

    private static final Scanner SCANNER = new Scanner(System.in);
    private long MEETING_DURATION;
    private final List<LocalTime> POSSIBLE_HOURS = new ArrayList<>();

    void getMeetingDuration() {

        System.out.print("Please, input the meeting duration (HH:MM): ");

        while (true) {

            String meetingDuration = SCANNER.next();

            try {

                String[] array = meetingDuration.split(":");
                this.MEETING_DURATION = Long.parseLong(array[0]) * 60 + Long.parseLong(array[1]);
                break;

            } catch (DateTimeParseException | NumberFormatException f) {

                System.out.print("Please, input hour in HH:MM format: ");

            }

        }

    }

    List<LocalTime> getPOSSIBLE_HOURS() {
        return POSSIBLE_HOURS;
    }

    long getMEETING_DURATION() {
        return MEETING_DURATION;
    }

    void setPossibleHours(List<LocalTime> possibleHours, LocalTime firstStart, LocalTime secondStart, LocalTime firstEnd, LocalTime secondEnd) {

        LocalTime start = secondStart.isAfter(firstStart) ? secondStart : firstStart;
        LocalTime end = secondEnd.isBefore(firstEnd) ? secondEnd : firstEnd;

        this.POSSIBLE_HOURS.add(start);

        for (int index = 0; MINUTES.between(possibleHours.get(index), end) > MEETING_DURATION; index++) {

            LocalTime hour = possibleHours.get(index).plusMinutes(MEETING_DURATION);
            this.POSSIBLE_HOURS.add(hour);

        }

        this.POSSIBLE_HOURS.add(end);

    }

    void deleteCommonHours(List<LocalTime> list) {
        this.POSSIBLE_HOURS.removeAll(list);
    }

    void deleteHoursBetweenStartsAndEnds(List<LocalTime> possibleHours) {

        List<LocalTime> hoursBetween = new ArrayList<>();

        for (int i = 1; i < possibleHours.size() - 1; i++) {

            boolean isBefore = false;
            boolean isAfter = false;

            for (int j = i - 1; j <= i + 1; j++) {

                if (j == i - 1 && MINUTES.between(possibleHours.get(j), possibleHours.get(i)) <= MEETING_DURATION) {

                    isBefore = true;

                }

                if (j == i + 1 && MINUTES.between(possibleHours.get(i), possibleHours.get(j)) <= MEETING_DURATION) {

                    isAfter = true;

                }

            }

            if (isBefore && isAfter) {

                hoursBetween.add(possibleHours.get(i));

            }

        }

        this.POSSIBLE_HOURS.removeAll(hoursBetween);

    }

}