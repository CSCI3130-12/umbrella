package com.umbrella.umbrella;

/**
 * Created by samdoiron on 2018-03-16.
 */

enum DayOfWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

class LectureLabTime {
    public TimeOfDay timeOfDay;
    public DayOfWeek day;

    public LectureLabTime(TimeOfDay timeOfDay, DayOfWeek dayOfWeek) {
        this.timeOfDay = timeOfDay;
        this.day = dayOfWeek;
    }
}
