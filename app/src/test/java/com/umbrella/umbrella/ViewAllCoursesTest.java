package com.umbrella.umbrella;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ben Baker on 2018-02-22.
 */
public class ViewAllCoursesTest {
    @Test
    public void returnsCoursesFromRepo() {
        CourseSet expectedCourses = new CourseSet();
        expectedCourses.addCourse(new Course("0", "CSCI3130", "Software Engineering"));
        FakeCourseRepo repo = new FakeCourseRepo(expectedCourses);

        ViewAllCourses useCase = new ViewAllCourses(repo);

        CourseSet actualCourses = useCase.viewAllCourses();
        assertTrue(expectedCourses.equals(actualCourses));
    }
}