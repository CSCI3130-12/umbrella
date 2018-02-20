package com.umbrella.umbrella;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by samdoiron on 2018-02-20.
 * This is a view model containing the data necessary to display
 * a listing of courses to a user.
 */

public class ViewCoursesViewModel {
    /**
     * Get the list of all course listing view models (unfiltered)
     * with a non-guaranteed order.
     *
     * @return The courses that should be displayed
     */
    Collection<CourseListingViewModel> getCourses() {
        ArrayList<CourseListingViewModel> courses = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            courses.add(new CourseListingViewModel());
        }
        return courses;
    }
}
