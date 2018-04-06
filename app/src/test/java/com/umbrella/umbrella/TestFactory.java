package com.umbrella.umbrella;

/**
 * Created by samdoiron on 2018-03-23.
 */

public class TestFactory {
    int count = 0;

    public Course fakeCourse() {
        return new Course(
            "crn-"+nextCount(),
            "FAKE-"+nextCount(),
            "Fake Course " + nextCount()
        );
    }

    public LectureLab fakeLectureLabForCourse(Course course) {
        return new LectureLab(
                course,
                "Instructor #"+nextCount(),
                "Room " + nextCount(),
                100,
                80
        );
    }

    private int nextCount() {
        return count++;
    }

    public LectureLab fakeLectureLab() {
        return fakeLectureLabForCourse(fakeCourse());
    }
}
