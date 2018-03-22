package com.umbrella.umbrella;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by braden11 on 3/18/2018.
 */

public class CourseDetailViewPresenter {
    private Course course;
    private String name;
    private String courseID;
    private String description;
    private String coRequisite;
    private String preRequisite;

    public CourseDetailViewPresenter(Course course){
        name = course.getCourseName();
        courseID = course.getCourseID();
        description = course.getDescription();
        coRequisite = prepareRequisites(course.getCorequisite());
        preRequisite = prepareRequisites(course.getPrerequisite());

    }


    public String getName() {
        return name;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getDescription() {
        return description;
    }

    public String getCoRequisite() {
        return coRequisite;
    }

    public String getPreRequisite() {
        return preRequisite;
    }

    /**
     * Creates a comma seperated version of all the items in the  course set.
     * Could potentially be migrated as a toString method for CourseSet
     * @param courses
     * @return
     */
    public String prepareRequisites(CourseSet courses){
        String requisite =  "";
        Collection<Course> courseList = courses.getCoursesValues();

        Iterator<Course> iter = courseList.iterator();

        if (courseList.size() != 0){
            requisite = iter.next().getCourseID();
        }

        while (iter.hasNext()){
            requisite += ", " + iter.next();
        }
        return requisite;
    }
}

