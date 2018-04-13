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
    private ViewCoursesViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_course, container, false);
        ActiveUser student = getActivity().getIntent().getParcelableExtra("USER");

        appData = (ApplicationData) getActivity().getApplicationContext();
        presenter = new ViewCoursesPresenter(new DatabaseRegistrationRepo(student.getUsername(), appData.dbReference));
        ListView listView = view.findViewById(R.id.list);

        presenter.setOnViewModelChanged(viewModel -> {
            this.viewModel = viewModel;

            System.out.println("Updating data adapter");
            listView.setAdapter(dataAdapter());
            return null;
        });

        viewModel = presenter.getViewModel();
        listView.setAdapter(dataAdapter());

        return view;

    }

    /**
     * Adapts the course data provided by the view model into a form that the
     * android list view wants.
     * @return an ArrayAdapter that can be used with a list view.
     */
    ArrayAdapter dataAdapter() {
        ArrayList<CourseListingViewModel> listings = viewModel.courses;
        return new ArrayAdapter<>(
                MyCourseFragment.this.getActivity(),
                android.R.layout.simple_list_item_1,
                listings
        );
    }
}
