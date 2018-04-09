package com.umbrella.umbrella;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Ben Baker on 2018-02-22.
 * The presenter used to present courses
 */

public class ViewCoursesPresenter {
    private final ViewAllCourses viewAllCourses;
    private CourseSet courses;

    public ViewCoursesPresenter(CourseRepo repo) {
        this.viewAllCourses = new ViewAllCourses(repo);
    }

    /**
     * Builds a ViewModel with the current course set and sorts it
     * @return A sorted CourseSet
     */
    public ViewCoursesViewModel getViewModel() {
        ArrayList<CourseListingViewModel> courseViewModels = new ArrayList<>();
        ArrayList<Course> sortedCourses = sortAlphabeticallyByID(courses.getCourseSet().values());
        for (Course course : sortedCourses) {
            courseViewModels.add(viewModelForCourse(course));
        }
        ViewCoursesViewModel viewModel = new ViewCoursesViewModel();
        viewModel.courses = courseViewModels;
        return viewModel;
    }

    /**
     * Refreshes the data in the CourseSet
     */
    public void refreshData() {
        this.courses = viewAllCourses.viewAllCourses();
    }

    /**
     * Creates a new CourseListingViewModel with a given course
     * @param course The course to create a view model for
     * @return A CourseListingViewModel to display the course
     */
    private CourseListingViewModel viewModelForCourse(Course course) {
        CourseListingViewModel viewModel = new CourseListingViewModel();
        viewModel.name = course.getCourseName();
        return viewModel;
    }

    public void pushToAdapter(ArrayAdapter<CourseListingViewModel> adapter){
        adapter.addAll(getViewModel().getCourses());
    }

    /**
     * Alphabetically sorts the given Collection by name
     * @param courses A collection of courses
     * @return A sorted ArrayList of courses
     */
    private ArrayList<Course> sortAlphabeticallyByID(Collection<Course> courses) {
        ArrayList<Course> result = new ArrayList<>();
        for(Course course : courses){
            result.add(course);
        }
        Collections.sort(result, new Comparator<Course>() {
            @Override
            public int compare(Course one, Course two) {
                return one.getCourseName().compareTo(two.getCourseName());
            }
        });
        return result;
    }
}
