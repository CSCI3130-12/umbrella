package com.umbrella.umbrella;

import java.util.ArrayList;

/**
 * Created by samdoiron on 2018-04-06.
 */

public class ViewCoursesPresenter {
    private final CourseRepo repo ;

    public ViewCoursesPresenter(CourseRepo repo) {
        this.repo = repo;
    }

    public ViewCoursesViewModel getViewModel() {
        ArrayList<CourseListingViewModel> vms = new ArrayList<CourseListingViewModel>();
        for (Course course : repo.getAllCourses()) {
            vms.add(forCourse(course));
        }
        return new ViewCoursesViewModel(vms);
    }

    private CourseListingViewModel forCourse(Course course) {
        return new CourseListingViewModel(course.getCourseName());
    }
}
