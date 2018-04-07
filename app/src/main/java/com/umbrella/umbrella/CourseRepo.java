package com.umbrella.umbrella;

/**
 * Created by samdoiron on 2018-04-04.
 */

interface CourseRepo {
    public CourseSet getAllCourses();
    public Course getCourse(String courseID);
}
