package com.umbrella.umbrella;

/**
 * Created by Ben Baker on 2018-02-22.
 * An implementation of the course repo containing faked out data
 * to hold the place of the database for testing
 */

public class FakeCourseRepo implements CourseRepo {

    private final CourseSet courses;

    /**
     * Creates a new course repo given a set of courses
     * @param courses The CourseSet to make populate the repo
     */
    public FakeCourseRepo(CourseSet courses) {
        this.courses = courses;
    }

    /**
     * Returns the set of courses for this repo
     * @return A CourseSet containing all the courses
     */
    @Override
    public CourseSet getAllCourses() {
        return courses;
    }
}
