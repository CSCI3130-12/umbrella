package com.umbrella.umbrella;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;


/**
 * A fragment to view a specific users registered courses
 */
public class MyCourseFragment extends Fragment {

    public static ViewCoursesPresenter presenter;
    public static ArrayAdapter adapter;
    private ApplicationData appData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_course, container, false);
        ActiveUser student = getActivity().getIntent().getParcelableExtra("USER");

        appData = (ApplicationData) getActivity().getApplicationContext();
        preparePresenter(view,student.getUsername());

        return view;

    }

    /**
     * Prepares the presenter and ties it to the ListView
     * @param view The view for the fragment
     */
    public void preparePresenter(View view, String student){

        presenter = new ViewCoursesPresenter(new MyCourseRepo(appData.dbReference,student));
        presenter.refreshData();
        ListView listView = view.findViewById(R.id.list);

        adapter = dataAdapter();
        listView.setAdapter(adapter);
    }

    /**
     * Adapts the course data provided by the view model into a form that the
     * android list view wants.
     * @return an ArrayAdapter that can be used with a list view.
     */
    ArrayAdapter dataAdapter() {
        /*
        ViewCoursesViewModel viewModel = presenter.getViewModel();

        Collection<CourseListingViewModel> courses = viewModel.courses;
        CourseListingViewModel listings[] = new CourseListingViewModel[courses.size()];
        courses.toArray(listings);
        */

        ArrayList<CourseListingViewModel> listings = presenter.getViewModel().getCourses();
        return new ArrayAdapter<>(
                MyCourseFragment.this.getActivity(),
                android.R.layout.simple_list_item_1,
                listings
        );
    }
    
    /**
     *
     * @return a CourseRepo for the current user
     */
    CourseRepo getCourseRepo(){
        CourseSet courses = new CourseSet();

        courses.addCourse(new Course("1","test","testname"));

        return new FakeCourseRepo(courses);
    }

}
