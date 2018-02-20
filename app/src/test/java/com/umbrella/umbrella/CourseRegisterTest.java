package com.umbrella.umbrella;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wauch on 2018-02-19.
 */

public class CourseRegisterTest {




    @Test
    public void AddCourse() throws Exception {
        Course course = new Course("10", "CSCI1000");
        CourseController controller = new CourseController();
        Student student = new Student();
        controller.addCourse(course, student);
        assertEquals (true, student.hasCourse(course));
    }

    @Test
    public void FindNotAddedCourse() throws Exception {
        Course course = new Course("10", "CSCI1000");
        Course course2 = new Course("12", "INFX3000");
        CourseController controller = new CourseController();
        Student student = new Student();
        controller.addCourse(course, student);
        assertEquals (false, student.hasCourse(course2));
    }

    @Test
    public void AddCourseWithoutCoRequisite() throws Exception {
        Course course = new Course("10", "CSCI1000", "CRS");
        Course course2 = new Course("12", "CSCI1000LAB", "LAB");
        course.addCoReq(course2);
        course2.addCoReq(course);
        CourseController controller = new CourseController();
        Student student = new Student();

        controller.addCourse(course, student);

        assertEquals (false, student.hasCourse(course));
    }

    @Test
    public void AddCourseWithPreRequisite() throws Exception {
        CourseController controller = new CourseController();
        Student student = new Student();
        Course course = new Course("10", "CSCI1000", "CRS");
        Course course2 = new Course("12", "INFX2000", "CRS");
        course2.addPreReq(course);

        controller.addCourse(course, student);
        controller.addCourse(course2, student);

        assertEquals (true, student.hasCourse(course2));
    }

    @Test
    public void AddCourseWithoutPreRequisite() throws Exception {
        Course course = new Course("10", "CSCI1000", "CRS");
        Course course2 = new Course("12", "INFX2000", "CRS");
        course2.addPreReq(course);
        CourseController controller = new CourseController();
        Student student = new Student();

        controller.addCourse(course2, student);

        assertEquals (false, student.hasCourse(course2));
    }

    @Test
    public void AddCourseWithoutPreRequisiteInWrongOrder() throws Exception {
        Course course = new Course("10", "CSCI1000", "CRS");
        Course course2 = new Course("12", "INFX2000", "CRS");
        course2.addPreReq(course);
        CourseController controller = new CourseController();
        Student student = new Student();

        controller.addCourse(course2, student);
        controller.addCourse(course, student);

        assertEquals (false, student.hasCourse(course2)); //course2 requires course 1 to be added first
        assertEquals (true, student.hasCourse(course));
    }



}
