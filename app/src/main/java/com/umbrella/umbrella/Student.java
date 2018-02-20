package com.umbrella.umbrella;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by wauch on 2018-02-19.
 * A student class that extends user that has a list of courses that the user is signed up for
 */

public class Student extends User {


    LinkedList<Course> courseList;

    public  Student(){
        courseList = new LinkedList<Course>();

    }

    public LinkedList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(LinkedList<Course> courseList) {
        this.courseList = courseList;
    }

    public Student(String username, String password, LinkedList<Course> courseList){
       setUsername(username);
       setPassword(password);
       this.courseList = courseList;
   }

   public void addCourse(Course course){
       courseList.add(course);
   }

    /**
     * Checks if a student has a class in their registered classes.
     * @param course The course object
     * @return True if the course exists
     */
    public boolean hasCourse(Course course) {
        int i = 0;
        boolean found = false;
        while (!found && (i < courseList.size())) {
            if (courseList.get(i).getCourseID().equals(course.getCourseID())) {
                found = true;
            }
            i++;
        }
        return found;
    }

    /**
     * Checks if a student has a class in their registered classes.
     * @param courseID The ID of the course
     * @return True if the course exists
     */
    public boolean hasCourse(String courseID) {
        int i = 0;
        boolean found = false;
        while (!found || i < courseList.size()) {
            if (courseList.get(i).getCourseID().equals(courseID)) {
                found = true;
            }
            i++;
        }
        return found;
    }
}
