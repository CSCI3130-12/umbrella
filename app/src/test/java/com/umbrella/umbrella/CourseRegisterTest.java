package com.umbrella.umbrella;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**A test class for testing the AddCourse usecase
 * Created by wauch on 2018-02-19.
 */
public class CourseRegisterTest {

    Course course, course2, course3;
    AddCourse controller;
    Student student;

    @Before
    public void intialize() {
        course = new Course("1", "CSCI1000", "Computer Science 1");
        course2 = new Course("2", "INFX3000", "Database Studies");
        course3 = new Course("3", "INFX4000", "Datamining");
        controller = new AddCourse();
        student = new Student();
    }

    @Test
    public void AddCourse() throws Exception {
        controller.addCourse(course, student);
        assertEquals (true, student.hasCourse(course));
    }

    @Test
    public void FindNotAddedCourse() throws Exception {
        controller.addCourse(course, student);
        assertEquals (false, student.hasCourse(course2));
    }

    @Test
    public void AddCourseWithCoRequisite() throws Exception {
        course.addCorequisite(course2);
        controller.addCourse(course2, student);
        controller.addCourse(course, student);

        assertEquals( true, student.hasCourse(course));

    }

    @Test
    public void AddCourseWithoutCoRequisite() throws Exception {
        course.addCorequisite(course2);
        course2.addCorequisite(course);
        controller.addCourse(course, student);

        assertEquals (false, student.hasCourse(course));
    }

    @Test
    public void AddCourseWithPreRequisite() throws Exception {
        course2.addPrerequisite(course);
        controller.addCourse(course, student);
        controller.addCourse(course2, student);

        assertEquals (true, student.hasCourse(course2));
    }

    @Test
    public void AddCourseWithoutPreRequisite() throws Exception {
        course2.addPrerequisite(course);
        controller.addCourse(course2, student);

        assertEquals (false, student.hasCourse(course2));
    }

    @Test
    public void AddCourseWithoutPreRequisiteInWrongOrder() throws Exception {
        course2.addPrerequisite(course);
        controller.addCourse(course2, student);
        controller.addCourse(course, student);

        assertEquals (false, student.hasCourse(course2)); //course2 requires course 1 to be added first
        assertEquals (true, student.hasCourse(course));
    }

    @Test
    public void GetListofIntersectingCoursesTest() throws Exception {
        CourseSet courseSet = new CourseSet();
        courseSet.addCourse(course);
        courseSet.addCourse(course2);
        CourseSet courseSet2 = new CourseSet();
        courseSet2.addCourse(course);
        courseSet2.addCourse(course2);
        courseSet2.addCourse(course3);
        course2.addPrerequisite(course);

        assertEquals (true, courseSet.intersectingCourses(courseSet2).hasCourse(course));
    }

    @Test
    public void GetListofIntersectingCoursesFalseTest() throws Exception {
        CourseSet courseSet = new CourseSet();
        courseSet.addCourse(course);
        courseSet.addCourse(course2);
        CourseSet courseSet2 = new CourseSet();
        courseSet2.addCourse(course);
        courseSet2.addCourse(course2);
        courseSet2.addCourse(course3);
        course2.addPrerequisite(course);
        //test should result in course 3 not being the in the resulting intersection and hence false
        assertEquals (false, courseSet.intersectingCourses(courseSet2).hasCourse(course3));
    }




}
