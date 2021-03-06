package com.umbrella.umbrella;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Ben Baker on 2018-02-22.
 */
public class ViewCoursesPresenterTest {

    @Test
    public void usesDataFromRepo() {
        CourseSet courses = new CourseSet();
        courses.addCourse(new Course("CSCI1000","Computer Science I", "Desc."));
        FakeCourseRepo fakeRepo = new FakeCourseRepo(courses);

        ViewCoursesPresenter presenter = new ViewCoursesPresenter(fakeRepo);
        presenter.refreshData();
        ViewCoursesViewModel viewModel = presenter.getViewModel();

        assertEquals("Computer Science I", viewModel.courses.get(0).name);
    }

    @Test
    public void coursesOrderedAlphabeticallyByID() {
        CourseSet courses = new CourseSet();
        courses.addCourse(new Course("CSCI2000","Computer Science II"));
        courses.addCourse(new Course("HIST4100","History of 1567"));
        courses.addCourse(new Course("MATH3000","Big Math"));
        courses.addCourse(new Course("CSCI1500","Introduction to Typing"));
        FakeCourseRepo fakeRepo = new FakeCourseRepo(courses);

        ViewCoursesPresenter presenter = new ViewCoursesPresenter(fakeRepo);
        presenter.refreshData();
        ViewCoursesViewModel viewModel = presenter.getViewModel();

        ArrayList<String> expectedNames = new ArrayList<>();
        expectedNames.add("Big Math");
        expectedNames.add("Computer Science II");
        expectedNames.add("History of 1567");
        expectedNames.add("Introduction to Typing");

        assertEquals(expectedNames, getCourseNames(viewModel));
    }

    public ArrayList<String> getCourseNames(ViewCoursesViewModel viewModel) {
        ArrayList<String> courseNames = new ArrayList<>();
        for (CourseListingViewModel courseListingViewModel : viewModel.courses){
            courseNames.add(courseListingViewModel.name);
        }
        return courseNames;
    }
}