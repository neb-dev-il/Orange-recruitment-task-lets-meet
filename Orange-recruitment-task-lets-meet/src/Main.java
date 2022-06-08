
class Main {

    public static void main(String[] args) {

        Person firstPerson = new Person(Person.getStart(), Person.getEnd(), Person.getPlannedMeetingList());
        Person secondPerson = new Person(Person.getStart(), Person.getEnd(), Person.getPlannedMeetingList());
        firstPerson.deleteAllOccurrencesOfDuplicateHours(firstPerson.getStartsAndEndsOfPlannedMeetings());
        secondPerson.deleteAllOccurrencesOfDuplicateHours(secondPerson.getStartsAndEndsOfPlannedMeetings());
        Timetable timetable = new Timetable(Timetable.getMeetingDuration());
        timetable.setPossibleHours(timetable.getPossibleHours(), firstPerson.getSTART_TIME(), secondPerson.getSTART_TIME(), firstPerson.getEND_TIME(), secondPerson.getEND_TIME());
        timetable.deleteCommonHours(firstPerson.getHoursBetweenStartAndEnd(firstPerson.getStartsAndEndsOfPlannedMeetings(), timetable.getMEETING_DURATION()));
        timetable.deleteCommonHours(secondPerson.getHoursBetweenStartAndEnd(secondPerson.getStartsAndEndsOfPlannedMeetings(), timetable.getMEETING_DURATION()));
        timetable.deleteHoursBetweenStartsAndEnds(timetable.getPossibleHours());
        System.out.println(timetable.getPossibleHours());

    }

}