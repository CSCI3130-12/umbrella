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
    private HashMap<String, Course> courseSet;

    public CourseSet() {
        courseSet = new HashMap<>();

    }

    public Collection<Course> getCourses(){
        return courseSet.values();
    }

    public  HashMap<String, Course> getCourseSet() {
        return courseSet;
    }

    public void setCourseSet(HashMap<String, Course> courseSet) {
        courseSet = courseSet;
    }

    /**
     * Adds a course to the hashmap using its CRN as the key.
     * @param course The course object to be added
     */
    public void addCourse(Course course){
        courseSet.put(course.getCRN(), course);

    }

    /**
     * Removes a course from the hashmap using its CRN as the key.
     * @param course The course object to be added.
     */
    public void removeCourse(Course course){
        courseSet.remove(course.getCRN());
    }

    /**
     * Checks if the courseset has a course using it's CRN.
     * @param course The course object to be searched for.
     * @return True if the course is in the set.
     */
    public boolean hasCourse(Course course){
        boolean result = courseSet.containsKey(course.getCRN());

        return result;

    }

    /**
     * Finds a course object using it's CRN
     * @param CRN
     * @return Course object
     */
    public Course getCourseByCRN(String CRN ){
        Course course = new Course();
        course = courseSet.get(CRN);
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
        Set<String> courseThisSet = this.courseSet.keySet();

        intersectCourse.retainAll(courseThisSet);
        return (!intersectCourse.isEmpty());
    }

    /**
     * Returns a CourseSet of intersecting courses.
     * @param courseSet The courseset to be intersected with.
     * @return CourseSet of intersecting courses.
     */
    public CourseSet intersectingCourses(CourseSet courseSet) {
        CourseSet missingCourses = new CourseSet();
        String CRN;
        Course course;
        try {

            if (courseSet.getCourseSet().isEmpty()) {
                return missingCourses;
            }

            Set<String> intersectCourses = courseSet.getCourseSet().keySet();
            Set<String> courseThisSet = this.courseSet.keySet();

            Iterator<String> iter = courseThisSet.iterator();

            while (iter.hasNext()) {
                CRN = iter.next();
                course = this.getCourseByCRN(CRN);
                missingCourses.addCourse(course);
            }

       }
        catch (Exception E) {
                //System.out.println(E.getLocalizedMessage());
       }
        return missingCourses;
    }
}
