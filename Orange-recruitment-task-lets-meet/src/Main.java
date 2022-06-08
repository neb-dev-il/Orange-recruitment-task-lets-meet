
class Main {

    public static void main(String[] args) {

        Person firstPerson = new Person();
        Person secondPerson = new Person();
        Timetable timetable = new Timetable();

        firstPerson.getStart();
        firstPerson.getEnd();
        firstPerson.getMeetingsStartsAndEnds();

        secondPerson.getStart();
        secondPerson.getEnd();
        secondPerson.getMeetingsStartsAndEnds();

        timetable.getMeetingDuration();

        firstPerson.deleteAllOccurrencesOfDuplicateHours(firstPerson.getMEETINGS_STARTS_AND_ENDS());
        secondPerson.deleteAllOccurrencesOfDuplicateHours(secondPerson.getMEETINGS_STARTS_AND_ENDS());

        timetable.setPossibleHours(timetable.getPOSSIBLE_HOURS(), firstPerson.getSTART_TIME(), secondPerson.getSTART_TIME(), firstPerson.getEND_TIME(), secondPerson.getEND_TIME());
        timetable.deleteCommonHours(firstPerson.getHoursBetweenStartAndEnd(firstPerson.getMEETINGS_STARTS_AND_ENDS(), timetable.getMEETING_DURATION()));
        timetable.deleteCommonHours(secondPerson.getHoursBetweenStartAndEnd(secondPerson.getMEETINGS_STARTS_AND_ENDS(), timetable.getMEETING_DURATION()));
        timetable.deleteHoursBetweenStartsAndEnds(timetable.getPOSSIBLE_HOURS());
        
        System.out.println(timetable.getPOSSIBLE_HOURS());

    }

}