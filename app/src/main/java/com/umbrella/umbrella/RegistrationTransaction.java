package com.umbrella.umbrella;

import java.util.ArrayList;

/**
 * Validates whether a set of modifications to a student's
 * registered courses leaves that set in a valid state.
 */
public class RegistrationTransaction {
    static final int MAX_CONCURRENT_COURSES = 6;

    final ArrayList<RegistrationOperation> operations;
    final Student originalStudent;
    Student workingStudent;

    public RegistrationTransaction(Student student) {
        operations = new ArrayList<>();
        this.originalStudent = student;
        this.workingStudent = new Student(student);
    }

    public boolean isValid() {
        performOperations();
        return workingStudent.getRegisteredCourseCount() <= MAX_CONCURRENT_COURSES;
    }

    public void addOperation(RegistrationOperation operation) {
        operations.add(operation);
    }

    private void performOperations() {
        workingStudent = new Student(originalStudent);
        for (RegistrationOperation op : operations) {
            op.perform(workingStudent);
        }
    }
}
