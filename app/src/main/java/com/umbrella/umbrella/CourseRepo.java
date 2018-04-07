package com.umbrella.umbrella;

/**
 * Created by Ben Baker on 2018-02-22.
 * An interface to interact with the course repo
 */

public interface CourseRepo {

    /**
     * Get all the courses in the repo
     * @return A CourseSet containing all the courses
     */
    public CourseSet getAllCourses();
}
