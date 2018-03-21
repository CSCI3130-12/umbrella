package com.umbrella.umbrella;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegistrationTransactionTest {
    @Test
    public void itCanBeCreated() {
        Student student = emptyStudent();
        RegistrationTransaction transaction = new RegistrationTransaction(student);
    }

    @Test
    public void emptyRegistrationIsValid() {
        RegistrationTransaction transaction = new RegistrationTransaction(emptyStudent());
        assertTrue(transaction.isValid());
    }

    @Test
    public void youCantRegisterForTooManyCourses() {
        RegistrationTransaction transaction = new RegistrationTransaction(emptyStudent());
        LectureLab fakeLecture = new LectureLab("Neil Armstrong", "Moon");

        for (int i  = 0; i < RegistrationTransaction.MAX_CONCURRENT_COURSES + 1; i++) {
            transaction.addOperation(new RegisterForLectureOrLabOperation(fakeLecture));
        }

        assertFalse(transaction.isValid());
    }

    private Student emptyStudent() {
        return new Student(
                "John Doe",
                "hunter2",
                new CourseSet(),
                new LectureLabSet()
        );
    }
}