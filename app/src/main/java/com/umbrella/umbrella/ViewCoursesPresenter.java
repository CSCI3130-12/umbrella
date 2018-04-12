package com.umbrella.umbrella;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Ben Baker on 2018-02-22.
 * The presenter used to present courses
 */

public class ViewCoursesPresenter {
    private final ViewAllCourses viewAllCourses;
    private CourseSet courses;
    private Function<ViewCoursesViewModel, Void> onViewModelChanged;

    public void setOnViewModelChanged(Function<ViewCoursesViewModel, Void> onViewModelChanged) {
        this.onViewModelChanged = onViewModelChanged;
    }


    /**
     * A constructor to create the presenter and refresh its data
     * @param repo The CourseRepo to populate the presenter
     */
    public ViewCoursesPresenter(CourseRepo repo) {
        this.viewAllCourses = new ViewAllCourses(repo);
        this.refreshData();
        onViewModelChanged = x -> null;
        courses = new CourseSet();
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
        return new ViewCoursesViewModel(courseViewModels);
    }

    /**
     * Refreshes the data in the CourseSet
     */
    public void refreshData() {
        viewAllCourses.viewAllCourses().thenAccept(courses -> {
            System.out.println("Got all courses " + courses.size());
            this.courses = courses;
            signalViewModelChanged();
        });
    }

    private void signalViewModelChanged() {
        System.out.println("========== SIGNAL");
        this.onViewModelChanged.apply(getViewModel());
    }

    /**
     * Creates a new CourseListingViewModel with a given course
     * @param course The course to create a view model for
     * @return A CourseListingViewModel to display the course
     */
    private CourseListingViewModel viewModelForCourse(Course course) {
        return new CourseListingViewModel(
                course.getCourseID(),
                course.getCourseName()
        );
    }

    /**
     * Updates the array adapter with the new CourseListingViewModels
     * @param adapter The adapter to push the new information to
     */
    public void pushToAdapter(ArrayAdapter<CourseListingViewModel> adapter){
        adapter.addAll(getViewModel().courses);
    }

    /**
     * Alphabetically sorts the given Collection by name
     * @param courses A collection of courses
     * @return A sorted ArrayList of courses
     */
    private ArrayList<Course> sortAlphabeticallyByID(Collection<Course> courses) {
        ArrayList<Course> result = new ArrayList<>();
        result.addAll(courses);
        Collections.sort(result, new Comparator<Course>() {
            @Override
            public int compare(Course one, Course two) {
                return one.getCourseName().compareTo(two.getCourseName());
            }
        });
        return result;
    }
}
