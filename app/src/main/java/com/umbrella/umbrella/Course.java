package com.umbrella.umbrella;

import java.util.LinkedList;

/**
 * Created by wauch on 2018-02-19.
 * A course class that holds course-related information such as pre-requisites and IDs
 */

public class Course {
    /*To have attributes added as needed, modify after DB integration*/
    private String crn;
    private String courseID;
    private String courseName;



    private String description;
    public enum CourseType {
    CRS, TUT, LAB
    }
    private CourseType courseType;
    private String Faculty;
    private CourseSet preRequisite;
    private CourseSet coRequisite;

    public Course(){
        preRequisite = new CourseSet();
        coRequisite = new CourseSet();
        courseType = CourseType.CRS;
    }

    public Course(String crn, String courseID, String courseName) {
        preRequisite = new CourseSet();
        coRequisite = new CourseSet();
        this.crn = crn;
        this.courseID = courseID;
        this.courseName = courseName;
    }

    public Course(String crn, String courseID, String courseName, String courseType) {
        preRequisite = new CourseSet();
        coRequisite = new CourseSet();
        this.crn = crn;
        this.courseID = courseID;
        this.courseName = courseName;

        if(courseType.equals("CRS")){
            this.courseType = CourseType.CRS;
        }

        if(courseType.equals("LAB")){
            this.courseType = CourseType.LAB;
        }

        if(courseType.equals("TUT")){
            this.courseType = CourseType.TUT;
        }
    }


    public Course(String crn, String courseID, String courseName, CourseSet preRequisite, CourseSet coRequisite) {
        this.preRequisite = preRequisite;
        this.coRequisite = coRequisite;
        this.crn = crn;
        this.courseID = courseID;
        this.courseName = courseName;
    }

    public String getcrn() {
        return crn;
    }

    /**
     * Sets the the CRN value (course registration number) which will be used as a primary key
     * @param crn Course registration number used for a primary key
     */
    public void setcrn(String crn) {
        this.crn = crn;
    }

    /**
     * Gets the course ID, i.e. "CSCI 1000"
     * @return courseID a String
     */
    public String getCourseID() {

        return courseID;
    }

    /**
     * Sets the course ID, i.e. "CSCI 1000"
     * @param courseID String Value
     */
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    /**
     * Gets the course Name i.e. "Integrated Studies"
     * @return courseName, a String value
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the course name, i.e. "Computer Science 1"
     * @param courseName String value
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Gets the description of the course
     * @return description String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the course
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Adds a preqrequisite course to the student's list of prerequisite courses.
     * @param course A course object.
     */
    public void addPrerequisite(Course course){
        preRequisite.addCourse(course);
    }

    /**
     * Adds a corequisite course to the student's list of corequisite courses.
     * @param course A course object.
     */
    public void addCorequisite(Course course){
        coRequisite.addCourse(course);
    }

    /**
     * Gets the list of prerequisite courses.
     * @return CourseSet a set of courses containing hashmap of prerequisite courses
     */
    public CourseSet getPrerequisite(){
        return preRequisite;
    }

    /**
     * Gets the list of corequisite courses.
     * @return CourseSet a set of courses containing hashmap of corequisite courses
     */
    public CourseSet getCorequisite(){
        return coRequisite;
    }

}
