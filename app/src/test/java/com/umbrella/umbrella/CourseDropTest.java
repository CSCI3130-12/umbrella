package com.umbrella.umbrella;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**A test class for testing the DropCourse usecse
 * Created by Ben Baker on 2018-03-23.
 */
public class CourseDropTest {

    private Course course, course2, course3;
    private DropCourse controller;
    private Student student;

    @Before
    public void initialize() {
        course = new Course("1", "CSCI1000", "Computer Science 1");
        course2 = new Course("2", "INFX3000", "Database Studies");
        course3 = new Course("3", "INFX4000", "Datamining");
        controller = new DropCourse();
        student = new Student();
    }

    @Test
    public void DropCourse() throws Exception {
        controller.dropCourse(course, student);
        assertEquals (false, student.hasCourse(course));
    }
}
