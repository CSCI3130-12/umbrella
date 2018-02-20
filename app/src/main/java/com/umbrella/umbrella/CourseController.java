package com.umbrella.umbrella;

import java.util.LinkedList;

/**
 * Created by wauch on 2018-02-19.
 * A course for doing application logic around adding and removing courses from students
 */

public class CourseController {

    //how to do we deal with courses like lab+course that require each other? Let people add them but warn them?

    /**
     * Adds a course object to a student's list of courses
     * @param course The course to be added
     * @param student The student object to have the course added to
     */
    public void addCourse(Course course, Student student) {
        try {
            if (student.getCourseList().doesIntersect(course.getCorequisite()) &&
                   student.getCourseList().doesIntersect(course.getPrerequisite())) {
                student.addCourse(course);
            }

        } catch (Exception E) {
            System.out.println("Coreqs and prerequisites were probably not set.");
            //do nothing :D
        }
    }
}
