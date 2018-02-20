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
    public void addCourse(Course course, Student student){
        if(compareCourseLists(course.getPreReq(), student) && compareCourseLists(course.getCoReq(), student) ) {
            student.addCourse(course);
        }

    }

    /**
     * Compares two lists and stores the non-intersected values in a list. Used for determining
     * prerequisites and corequisite requirements.
     * @param courseList The list of courses to find in the student's course lists
     * @param student The student's list of already signed up courses
     * @return True if all prerequisites are met.
     */
    public boolean compareCourseLists(LinkedList<Course> courseList, Student student){
        LinkedList<Course> missingCourses = new LinkedList<>(); //can be used for error messages and other things later
        LinkedList<Course> studCourses = student.getCourseList();
        Boolean matchFound = false;

        for(int i = 0; i < courseList.size(); i++){
            int j = 0;
            matchFound = false;
            while(!matchFound && j < studCourses.size()){
                if(studCourses.get(j).getCourseID().equals(courseList.get(i).getCourseID()) ){
                    matchFound = true;
                }
                j++;
            }
            if(!matchFound){
                missingCourses.add(courseList.get(i));
            }
        }

        return (missingCourses.size() == 0); //return true if no courses requirements are missing
    }

}
