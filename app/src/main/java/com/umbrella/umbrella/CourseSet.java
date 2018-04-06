package com.umbrella.umbrella;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by wauch on 2018-02-20.
 * A hashmap of courses with various operations that can be performed on it as a set.
 * Used as an entity for use cases such as the student's list of courses.
 */

public class CourseSet implements Collection<Course>, Iterable<Course> {
    private HashMap<String, Course> courses;

    public CourseSet() {
        courses = new HashMap<>();
    }

    public CourseSet(CourseSet other) {
        courses = new HashMap<>();
        for (Course course : other.courses.values()) {
            Course clone = new Course(course);
            courses.put(clone.getCourseID(), clone);
        }
    }

    public Iterator<Course> iterator() {
        return courses.values().iterator();
    }

    public Collection<Course> getCoursesValues(){
        return courses.values();
    }

    public HashMap<String, Course> getCourseSet() {
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
    public void addCourse(Course course) {
        courses.put(course.getCourseID(), course);
    }

    /**
     * Removes a course from the hashmap using its crn as the key.
     * @param course The course object to be added.
     */
    public void removeCourse(Course course) {
        courses.remove(course.getCourseID());
    }

    /**
     * Checks if the courseset has a course using it's crn.
     * @param course The course object to be searched for.
     * @return True if the course is in the set.
     */
    public boolean hasCourse(Course course){
        return courses.containsKey(course.getCourseID());
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
     * @param courseSet The courseset to be intersected with.
     * @return CourseSet of intersecting courses.
     */
    public CourseSet intersectingCourses(CourseSet courseSet) {
        CourseSet missingCourses = new CourseSet();
        String crn;
        Course course;
        try {

            if (courseSet.getCourseSet().isEmpty()) {
                return missingCourses;
            }

            Set<String> intersectCourses = courseSet.getCourseSet().keySet();
            Set<String> courseThisSet = this.courses.keySet();

            Iterator<String> iter = courseThisSet.iterator();

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

    @Override
    public int size() {
        return courses.size();
    }

    public boolean containsAll(CourseSet other) {
        return courses.values().containsAll(other.courses.values());
    }
    @Override
    public boolean isEmpty() {
        return courses.isEmpty();
    }

    @Override
    public boolean contains(Object needle) {
        return needle instanceof Course && hasCourse((Course) needle);
    }

    @NonNull
    @Override
    public Iterator<Course> iterator() {
        return courses.values().iterator();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return courses.values().toArray();
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] target) {
        return courses.values().toArray(target);
    }

    @Override
    public boolean add(Course course) {
        boolean isNew = hasCourse(course);
        addCourse(course);
        return isNew;
    }

    @Override
    public boolean remove(Object toRemove) {
        if (toRemove instanceof Course) {
            boolean had = hasCourse((Course) toRemove);
            removeCourse((Course) toRemove);
            return had;
        } else {
            return false;
        }
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> search) {
        // XXX: Unimplemented
        for (Object course : search) {
            if (!courses.containsValue(course)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends Course> toAdd) {
        for (Course course : toAdd) {
            addCourse(course);
        }
        return true;
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> toRemove) {
        for (Object course : toRemove) {
            if (!(course instanceof Course)) continue;
            removeCourse((Course)course);
        }
        return true;
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> collection) {
        CourseSet toRetain = new CourseSet();
        toRetain.addAll((Collection<Course>) collection);
        courses = intersectingCourses(toRetain).courses;
        return true;
    }

    @Override
    public void clear() {
        courses = new HashMap<>();
    }
}
