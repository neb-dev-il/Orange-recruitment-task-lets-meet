import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.MINUTES;

class Timetable {

    private static final Scanner SCANNER = new Scanner(System.in);
    private final long MEETING_DURATION;
    private List<LocalTime> possibleHours = new ArrayList<>();

    public Timetable(String meetingDuration) {
        String[] array = meetingDuration.split(":");
        this.MEETING_DURATION = Long.parseLong(array[0]) * 60 + Long.parseLong(array[1]);
    }

    static String getMeetingDuration() {
        System.out.print("Please, input the meeting duration (HH:MM): ");
        return SCANNER.next();
    }

    List<LocalTime> getPossibleHours() {
        return possibleHours;
    }

    long getMEETING_DURATION() {
        return MEETING_DURATION;
    }

    void setPossibleHours(List<LocalTime> possibleHours, LocalTime firstStart, LocalTime secondStart, LocalTime firstEnd, LocalTime secondEnd) {

        LocalTime start = secondStart.isAfter(firstStart) ? secondStart : firstStart;
        LocalTime end = secondEnd.isBefore(firstEnd) ? secondEnd : firstEnd;

        this.possibleHours.add(start);

        for (int index = 0; MINUTES.between(possibleHours.get(index), end) > MEETING_DURATION; index++) {

            LocalTime hour = possibleHours.get(index).plusMinutes(MEETING_DURATION);
            this.possibleHours.add(hour);

        }

        this.possibleHours.add(end);

    }

    void deleteCommonHours(List<LocalTime> list) {
        this.possibleHours.removeAll(list);
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

        this.possibleHours.removeAll(hoursBetween);

    }

}