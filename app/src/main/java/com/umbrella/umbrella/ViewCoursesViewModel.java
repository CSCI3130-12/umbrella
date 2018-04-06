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
     * The list of all course listing view models (unfiltered)
     * with a non-guaranteed order.
     */
    public final Collection<CourseListingViewModel> courses;

    ViewCoursesViewModel(ArrayList<CourseListingViewModel> courses) {
        this.courses = courses;
    }
}
