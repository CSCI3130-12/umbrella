package com.umbrella.umbrella;

/**
 * Created by wauch on 2018-02-19.
 * A student class that extends user that has a list of courses that the user is signed up for
 */

public class Student extends User {
    private LectureLabSet registration;
    private CourseSet creditsAcquired;

    public Student() {
        registration = new LectureLabSet();
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
        this.registration = new LectureLabSet(other.registration);
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
        return registration.size();
    }

    public void registerFor(LectureLab toRegisterFor) {
        registration.add(toRegisterFor);
    }
}
