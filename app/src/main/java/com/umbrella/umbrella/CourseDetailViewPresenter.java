package com.umbrella.umbrella;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class CourseDetailViewPresenter {
    private final Course course;

    public CourseDetailViewPresenter(Course course) {
        this.course = course;
    }

    public CourseDetailViewModel getViewModel() {
        return new CourseDetailViewModel(
                course.getCourseID(),
                course.getCourseName(),
                course.getDescription()
        );
    }
}

