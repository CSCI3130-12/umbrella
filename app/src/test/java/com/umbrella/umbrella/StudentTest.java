package com.umbrella.umbrella;

import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {
    @Test
    public void canRegisterForLectureOrLab() throws DuplicateCourseException {
        LectureLab lecture = new LectureLab("Neil Armstrong", "Moon");
        Student student = new Student();
        student.registerFor(lecture);
        assertTrue(student.isRegisteredFor(lecture));
    }
}