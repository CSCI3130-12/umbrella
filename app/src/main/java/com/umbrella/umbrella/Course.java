package com.umbrella.umbrella;

import android.app.AuthenticationRequiredException;

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

    private final CourseSet preRequisite;
    private final CourseSet coRequisite;
    private RequiredLectureLabs requiredLectureLabs;

    public Course() {
        preRequisite = new CourseSet();
        coRequisite = new CourseSet();
        requiredLectureLabs = new RequiredLectureLabs();
    }
    public Course(Course course) {
        crn = course.crn;
        courseID = course.courseID;
        courseName = course.courseName;
        preRequisite = new CourseSet(course.preRequisite);
        coRequisite = new CourseSet(course.coRequisite);
        requiredLectureLabs = new RequiredLectureLabs(course.requiredLectureLabs);
    }

    public boolean canBeTakenWithCredits(CourseSet creditsAcquired) {
        return creditsAcquired.containsAll(preRequisite);
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Course) && ((Course)other).courseID.equals(courseID);
    }

    @Override
    public int hashCode() {
        return courseID.hashCode();
    }

    public Course(String crn, String courseID, String courseName) {
        preRequisite = new CourseSet();
        coRequisite = new CourseSet();
        this.crn = crn;
        this.courseID = courseID;
        this.courseName = courseName;
        requiredLectureLabs = new RequiredLectureLabs();
    }

    public Course(String crn, String courseID, String courseName, String courseType) {
        preRequisite = new CourseSet();
        coRequisite = new CourseSet();
        this.crn = crn;
        this.courseID = courseID;
        this.courseName = courseName;
        requiredLectureLabs = new RequiredLectureLabs();
    }


    public Course(String crn, String courseID, String courseName, CourseSet preRequisite, CourseSet coRequisite) {
        this.preRequisite = preRequisite;
        this.coRequisite = coRequisite;
        this.crn = crn;
        this.courseID = courseID;
        this.courseName = courseName;
        requiredLectureLabs = new RequiredLectureLabs();
    }

    public String getCRN() {
        return crn;
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

    public boolean canBeTakenGiven(CourseSet creditsAcquired, LectureLabSet registration) {
        if (!creditsAcquired.containsAll(preRequisite)) {
            return false;
        }

        for (Course coReq : coRequisite) {
            if (!(creditsAcquired.hasCourse(coReq) || registration.hasCourse(coReq))) {
                return false;
            }
        }

        // Required lecture-labs
        return requiredLectureLabs.isMetBy(registration);
    }

    public void setRequirements(RequiredLectureLabs requirements) {
        this.requiredLectureLabs = requirements;
    }
}
