package com.umbrella.umbrella;

import java.util.concurrent.CompletableFuture;

/**
 * Created by samdoiron on 2018-04-04.
 */

interface CourseRepo {
    /**
     * Get all the courses in the repo
     *
     * @return A CourseSet containing all the courses
     */
    public CompletableFuture<CourseSet> getAllCourses();

    /**
     * Get a specific course by its course id (CSCI-1111)
     *
     * @return A Course with the given id, or null.
     */
    public CompletableFuture<Course> getCourse(String courseID);
}

