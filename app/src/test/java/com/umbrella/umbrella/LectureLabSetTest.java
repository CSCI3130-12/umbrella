package com.umbrella.umbrella;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by samdoiron on 2018-03-23.
 */
public class LectureLabSetTest {
    @Test
    public void getCourseCountReturnsNumberOfDistinctCourses() {
        TestFactory factory = new TestFactory();
        Course courseOne = factory.fakeCourse();
        Course courseTwo = factory.fakeCourse();

        LectureLabSet set = new LectureLabSet();
        set.add(factory.fakeLectureLabForCourse(courseOne));
        set.add(factory.fakeLectureLabForCourse(courseTwo));
        set.add(factory.fakeLectureLabForCourse(courseTwo));

        assertEquals(2, set.getCourseCount());
    }
}