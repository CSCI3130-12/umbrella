package com.umbrella.umbrella;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by samdoiron on 2018-02-20.
 */

public class ViewCoursesViewModel {
    Collection<CourseListingViewModel> getCourses() {
        ArrayList<CourseListingViewModel> courses = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            courses.add(new CourseListingViewModel());
        }
        return courses;
    }
}
