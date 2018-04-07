package com.umbrella.umbrella;

/**
 * Created by Ben Baker on 2018-02-22.
 * A class containing functionality to view all the courses within a repo
 */

public class ViewAllCourses {

    private final CourseRepo repo;

    /**
     * Makes a new instance of the class with a given CourseRepo
     * @param repo A CourseRepo to pass to the class
     */
    public ViewAllCourses(CourseRepo repo) {
        this.repo = repo;
    }

    /**
     * Returns all the courses in the repo as a CourseSet
     * @return A CourseSet containing all the courses in the repo
     */
    public CourseSet viewAllCourses() {
        return repo.getAllCourses();
    }
}
