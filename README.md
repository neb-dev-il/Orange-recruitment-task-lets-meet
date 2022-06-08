# Orange-recruitment-task-lets-meet

This program implements an algorithm in Java that, based on the calendars of two people and the estimated length of the meeting, will propose possible meeting dates.
At the entrance:

- 2 people calendars with specific working hours and scheduled meetings
- estimated duration of the meeting

As a result, the program returns the ranges in which meetings can be arranged.

## Example

The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

<pre>
Please, input your start time of work (HH:MM): > 09:00
Please, input your end time of work (HH:MM): > 19:55
Please, input hours for all meetings in HH:MM format. After, input STOP
start: > 09:00
end: > 10:30
start: > 12:00
end: > 13:00
start: > 16:00
end: > 18:00
start: > stop
Please, input your start time of work (HH:MM): > 10:00
Please, input your end time of work (HH:MM): > 18:30
Please, input hours for all meetings in HH:MM format. After, input STOP
start: > 10:00
end: > 11:30
start: > 12:30
end: > 14:30
start: > 14:30
end: > 15:00
start: > 16:00
end: > 17:00
start: > stop
Please, input the meeting duration (HH:MM): > 00:30

Calendar 1
{
  working_hours: {
    start: "09:00",
    end: "19:55"
 },
planned_meeting: [
    start: "09:00",
    end: "10:30"
 },
    start: "12:00",
    end: "13:00"
 },
    start: "16:00",
    end: "17:00"
 },
]
}

Calendar 2
{
  working_hours: {
    start: "10:00",
    end: "18:30"
 },
planned_meeting: [
    start: "10:00",
    end: "11:30"
 },
    start: "12:30",
    end: "14:30"
 },
    start: "14:30",
    end: "15:00"
 },
    start: "16:00",
    end: "17:00"
 },
]
}

[11:30, 12:00, 15:00, 16:00, 17:00, 18:30]
</pre>