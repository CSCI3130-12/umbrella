package com.umbrella.umbrella;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by wauch on 2018-02-20.
 * A hashmap of courses with various operations that can be performed on it as a set.
 * Used as an entity for use cases such as the student's list of courses.
 */

public class CourseSet {
    private HashMap<String, Course> courses;

    public CourseSet() {
        courses = new HashMap<>();
    }

    public Collection<Course> getCoursesValues(){
        return courses.values();
    }

    public  HashMap<String, Course> getCourseSet() {
        return courses;
    }

    /**
     * Sets courses to a new hashmap
     * @param courses A hashmap of Hashmap<String, Course>
     */
    public void setCourses(HashMap<String, Course> courses) {
        this.courses = courses;
    }

    /**
     * Adds a course to the hashmap using its crn as the key.
     * @param course The course object to be added
     */
    public void addCourse(Course course){
        courses.put(course.getCrn(), course);
    }

    /**
     * Removes a course from the hashmap using its crn as the key.
     * @param course The course object to be added.
     */
    public void removeCourse(Course course){
        courses.remove(course.getCrn());
    }

    /**
     * Checks if the courseset has a course using it's crn.
     * @param course The course object to be searched for.
     * @return True if the course is in the set.
     */
    public boolean hasCourse(Course course){
        return courses.containsKey(course.getCrn());
    }

    /**
     * Finds a course object using it's crn
     * @param crn A course registration number that is used as a primary key in the DB
     * @return Course object
     */
    public Course getCourseByCrn(String crn ){
        Course course = new Course();
        course = courses.get(crn);
        return course;
    }

    /**
     * Checks if two coursesets have intersecting courses. Used to check if there are missing requisites.
     * @param courseSet The courseset to be intersected with.
     * @return True if there are intersecting courses.
     */
    public boolean doesIntersect(CourseSet courseSet){
        if(courseSet.getCourseSet().isEmpty()){
            return true;
        }

        Set<String> intersectCourse = courseSet.getCourseSet().keySet();
        Set<String> courseThisSet = this.courses.keySet();

        intersectCourse.retainAll(courseThisSet);
        return (!intersectCourse.isEmpty());
    }

    /**
     * Returns a CourseSet of intersecting courses.
     * @param theirCourses A CourseSet of courses to be compared to this objects
     * @return missingCourses A CourseSet of courses that do intersect
     */
    public CourseSet intersectingCourses(CourseSet theirCourses) {
        CourseSet resultingCourses = new CourseSet();
        String crn;
        Course course;
        try {

            if (theirCourses.getCourseSet().isEmpty()) {
                return resultingCourses;
            }

            Set<String> intersectCourses = theirCourses.getCourseSet().keySet();
            Set<String> ourCourses = this.courses.keySet();

            ourCourses.retainAll(intersectCourses);

            Iterator<String> iter = ourCourses.iterator();

            while (iter.hasNext()) {
                crn = iter.next();
                course = this.getCourseByCrn(crn);
                resultingCourses.addCourse(course);
            }

       }
        catch (Exception E) {
                //System.out.println(E.getLocalizedMessage());
       }
        return resultingCourses;
    }

    /**
     * Returns a CourseSet of courses that theirCourses do not intersect with (i.e. courses thay have not done)
     * @param theirCourses A CourseSet of courses to be compared to this objects
     * @return missingCourses A CourseSet of courses that do not intersect
     */
    public CourseSet nonIntersectingCourses(CourseSet theirCourses) {
        CourseSet missingCourses = new CourseSet();
        String crn;
        Course course;
        try {

            if (theirCourses.getCourseSet().isEmpty()) {
                return missingCourses;
            }

            Set<String> intersectCourses = theirCourses.getCourseSet().keySet();
            Set<String> ourCourses = this.courses.keySet();

            ourCourses.removeAll(intersectCourses);

            Iterator<String> iter = ourCourses.iterator();

            while (iter.hasNext()) {
                crn = iter.next();
                course = this.getCourseByCrn(crn);
                missingCourses.addCourse(course);
            }

        }
        catch (Exception E) {
            //System.out.println(E.getLocalizedMessage());
        }
        return missingCourses;
    }
}
