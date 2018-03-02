package com.umbrella.umbrella;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Ben Baker on 2018-02-22.
 */

public class ViewCoursesPresenter {
    private final ViewAllCourses viewAllCourses;
    private CourseSet courses;

    public ViewCoursesPresenter(CourseRepo repo) {
        this.viewAllCourses = new ViewAllCourses(repo);
    }

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

    public void refreshData() {
        this.courses = viewAllCourses.viewAllCourses();
    }

    private CourseListingViewModel viewModelForCourse(Course course) {
        CourseListingViewModel viewModel = new CourseListingViewModel();
        viewModel.name = course.getCourseName();
        return viewModel;
    }

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
