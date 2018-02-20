package com.umbrella.umbrella;

import java.util.LinkedList;

/**
 * Created by wauch on 2018-02-19.
 * A course class that holds course-related information such as pre-requisites and IDs
 */

public class Course {

    /*To have attributes added as needed, modify after DB integration*/
    private String CRN;
    private String courseID;
    private String courseName;
    public enum CourseType {
    CRS, TUT, LAB
    }
    private CourseType courseType;
    private String Faculty;
    private CourseSet preReq;
    private CourseSet coReq;

    public Course(){
        preReq = new CourseSet();
        coReq = new CourseSet();
        courseType = CourseType.CRS;

    }

    public Course(String CRN, String courseID, String courseName) {
        preReq = new CourseSet();
        coReq = new CourseSet();
        this.CRN = CRN;
        this.courseID = courseID;
        this.courseName = courseName;
    }

    public Course(String CRN, String courseID, String courseName, String courseType) {
        preReq = new CourseSet();
        coReq = new CourseSet();
        this.CRN = CRN;
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


    public Course(String CRN, String courseID, String courseName, CourseSet preReq, CourseSet coReq) {
        this.preReq = preReq;
        this.coReq = coReq;
        this.CRN = CRN;
        this.courseID = courseID;
        this.courseName = courseName;
    }

    public String getCRN() {
        return CRN;
    }

    public void setCRN(String CRN) {
        this.CRN = CRN;
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

    /**
     * Adds a preqrequisite course to the student's list of prerequisite courses.
     * @param course A course object.
     */
    public void addPrerequisite(Course course){
        preReq.addCourse(course);
    }

    /**
     * Adds a corequisite course to the student's list of corequisite courses.
     * @param course A course object.
     */
    public void addCorequisite(Course course){
        coReq.addCourse(course);
    }

    public CourseSet getPrerequisite(){
        return preReq;
    }

    public CourseSet getCorequisite(){
        return coReq;
    }

}
