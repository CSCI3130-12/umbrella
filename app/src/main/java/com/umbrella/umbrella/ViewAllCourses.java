package com.umbrella.umbrella;

/**
 * Created by Ben Baker on 2018-02-22.
 */

public class ViewAllCourses {

    private final CourseRepo repo;

    public ViewAllCourses(CourseRepo repo) {
        this.repo = repo;
    }

    public CourseSet viewAllCourses() {
        return repo.getAllCourses();
    }
}
