package com.umbrella.umbrella;

/**
 * Created by wauch on 2018-02-19.
 * A student class that extends user that has a list of courses that the user is signed up for
 */

public class Student extends User {
    public final static int MAX_COURSES = 6;
    private LectureLabSet registration;
    private CourseSet creditsAcquired;

    public Student() {
        registration = new LectureLabSet();
        creditsAcquired = new CourseSet();
    }

    public Student(String username, String password, CourseSet creditsAcquired,
                   LectureLabSet registration) {
        setUsername(username);
        setPassword(password);
        this.registration = registration;
        this.creditsAcquired = creditsAcquired;
    }

    /** Deep-clone a student from another */
    public Student(Student other) {
        super();
        setUsername(other.getUsername());
        setPassword(other.getPassword());
        this.registration = new LectureLabSet(other.registration);
        this.creditsAcquired = new CourseSet(other.creditsAcquired);
    }

    public boolean isValid() {
        return registration.getCourseCount() <= MAX_COURSES
                && hasRequiredLectureLabs()
                && noLectureLabsAreFull();
    }

    private boolean noLectureLabsAreFull() {
        for (LectureLab lectureLab : registration) {
            if (lectureLab.isFull()) {
                return false;
            }
        }
        return true;
    }

    private boolean hasRequiredLectureLabs() {
        for (Course course : registration.getCourses()) {
            if (!course.canBeTakenGiven(creditsAcquired, registration)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a student has a class in their registered classes.
     * @param needle The lecture or lab to heck.
     * @return True if the course exists
     */
    public boolean isRegisteredFor(LectureLab needle) {
        return registration.contains(needle);
    }

    public boolean hasCreditForCourse(Course course) {
        return false;
    }

    public int getRegisteredCourseCount() {
        return registration.getCourseCount();
    }

    public void registerFor(LectureLab toRegisterFor) {
        registration.add(toRegisterFor);
    }

    public void addCredit(Course course) {
        creditsAcquired.addCourse(course);
    }

    public LectureLabSet getRegistration() {
        return registration;
    }

    public CourseSet getRegisteredCourses() {
        try {
            return registration.getCourses();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
