package com.umbrella.umbrella;

import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {
    @Test
    public void canRegisterForLectureOrLab() throws DuplicateCourseException {
        Course astronomy = new Course("5555", "SPACE-101", "Astronomy");
        LectureLab lecture = new LectureLab(astronomy, "Neil Armstrong", "Moon", 10, 5);
        Student student = new Student();
        student.registerFor(lecture);
        assertTrue(student.isRegisteredFor(lecture));
    }
}