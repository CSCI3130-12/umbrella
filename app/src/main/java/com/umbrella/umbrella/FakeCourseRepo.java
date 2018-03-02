package com.umbrella.umbrella;

/**
 * Created by Ben Baker on 2018-02-22.
 */

public class FakeCourseRepo implements CourseRepo {

    private final CourseSet courses;

    public FakeCourseRepo(CourseSet courses) {
        this.courses = courses;
    }

    @Override
    public CourseSet getAllCourses() {
        return courses;
    }
}
