package com.umbrella.umbrella;

/**
<<<<<<< HEAD
 * Created by samdoiron on 2018-04-04.
 */

interface CourseRepo {
    /**
     * Get all the courses in the repo
     *
     * @return A CourseSet containing all the courses
     */
    public CourseSet getAllCourses();

    /**
     * Get a specific course by its course id (CSCI-1111)
     *
     * @return A Course with the given id, or null.
     */
    public Course getCourse(String courseID);
}

