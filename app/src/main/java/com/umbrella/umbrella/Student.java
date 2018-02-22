package com.umbrella.umbrella;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by wauch on 2018-02-19.
 * A student class that extends user that has a list of courses that the user is signed up for
 */

public class Student extends User {


    private CourseSet courseList;

    public  Student(){
        courseList = new CourseSet();
    }

    public CourseSet getCourseList() {
        return courseList;
    }

    public void setCourseList(CourseSet courseList) {
        this.courseList = courseList;
    }

    public Student(String username, String password, CourseSet courseList){
       setUsername(username);
       setPassword(password);
       this.courseList = courseList;
   }


    /**
     * Checks if a student has a class in their registered classes.
     * @param course The course object
     * @return True if the course exists
     */
    public boolean hasCourse(Course course) {
        return courseList.hasCourse(course);
    }

    public void addCourse(Course course) {
        courseList.addCourse(course);
    }

}
