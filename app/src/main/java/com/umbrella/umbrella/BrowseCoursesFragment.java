package com.umbrella.umbrella;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Collection;

/**
 * Created by samdoiron on 2018-02-16.
 *
 * This is the activity that shows a user a listing of courses that they
 * can register for.
 */

public class BrowseCoursesFragment extends Fragment {
    ViewCoursesViewModel viewModel = new ViewCoursesViewModel();
    CourseRepo repo;

    public void setArguments(Bundle bundle) {
        this.repo = (CourseRepo)bundle.get("courseRepo");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedBundleInstance) {
        View baseView = inflater.inflate(R.layout.registration, container, false);
        ListView listView = baseView.findViewById(R.id.course_list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity)getActivity()).switchToCourseDetails();
            }
        });
        return baseView;
    }

    /**
     * Adapts the course data provided by the view model into a form that the
     * android list view wants.
     * @return an ArrayAdapter that can be used with a list view.
     */
    ArrayAdapter dataAdapter() {
        Collection<CourseListingViewModel> courses = viewModel.getCourses();
        CourseListingViewModel listings[] = new CourseListingViewModel[courses.size()];
        courses.toArray(listings);

        return new ArrayAdapter<>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1,
                listings
        );
    }
}
