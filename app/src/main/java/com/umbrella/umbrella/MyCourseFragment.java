package com.umbrella.umbrella;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Collection;


/**
 * A fragment to view a specific users registered courses
 */
public class MyCourseFragment extends Fragment {

    ViewCoursesPresenter presenter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_course, container, false);
        preparePresenter(view);

        return view;

    }

    /**
     * Prepares the presenter and ties it to the ListView
     * @param view The view for the fragment
     */
    public void preparePresenter(View view){

        presenter = new ViewCoursesPresenter(getCourseRepo());
        presenter.refreshData();
        ListView listView = view.findViewById(R.id.list);

        listView.setAdapter(dataAdapter());
    }

    /**
     * Adapts the course data provided by the view model into a form that the
     * android list view wants.
     * @return an ArrayAdapter that can be used with a list view.
     */
    ArrayAdapter dataAdapter() {
        ViewCoursesViewModel viewModel = presenter.getViewModel();

        Collection<CourseListingViewModel> courses = viewModel.courses;
        CourseListingViewModel listings[] = new CourseListingViewModel[courses.size()];
        courses.toArray(listings);



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
