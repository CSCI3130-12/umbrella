package com.umbrella.umbrella;

/**
 * Created by Ben Baker on 2018-03-23.
 */

public class DropCourse {

    /**
     * Drops a course object from a student's list of courses
     * @param course The course to be dropped
     * @param student The student object to have the course dropped from
     */
    public void dropCourse(Course course, Student student) {
        if(student.getCourseList().hasCourse(course)){
            student.getCourseList().removeCourse(course);
        }
    }
}
