package com.umbrella.umbrella;

import java.util.LinkedList;

/**
 * Created by wauch on 2018-02-19.
 * A course class that holds course-related information such as pre-requisites and IDs
 */

public class Course {

    private String courseID;
    private String courseName;
    public enum CourseType {
    CRS, TUT, LAB
    }
    private CourseType courseType;
    private LinkedList<Course> preReq;
    private LinkedList<Course> coReq;

    public Course(){
        preReq = new LinkedList<>();
        coReq = new LinkedList<>();
        courseType = CourseType.CRS;

    }

    public Course(String courseID, String courseName) {
        preReq = new LinkedList<>();
        coReq = new LinkedList<>();
        this.courseID = courseID;
        this.courseName = courseName;
    }

    public Course(String courseID, String courseName, String courseType) {
        preReq = new LinkedList<>();
        coReq = new LinkedList<>();
        this.courseID = courseID;
        this.courseName = courseName;

        if(courseType.equals("0") || courseType.equals("CRS") || courseType.equals("course") || courseType.equals("crs")){
            this.courseType = CourseType.CRS;
        }

        if(courseType.equals("1") || courseType.equals("LAB") || courseType.equals("laboratory") || courseType.equals("lab")){
            this.courseType = CourseType.LAB;
        }

        if(courseType.equals("0") || courseType.equals("TUT") || courseType.equals("tutorial") || courseType.equals("tut")){
            this.courseType = CourseType.TUT;
        }

    }


    public Course(String courseID, String courseName, LinkedList<Course> preReq, LinkedList<Course> coReq) {
        this.preReq = preReq;
        this.coReq = coReq;
        this.courseID = courseID;
        this.courseName = courseName;
    }

    public String getCourseID() {

        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void addPreReq(Course course){
        preReq.add(course);
    }

    public void addCoReq(Course course){
        coReq.add(course);
    }

    public LinkedList<Course> getPreReq(){
        return preReq;
    }

    public LinkedList<Course> getCoReq(){
        return coReq;
    }

}
