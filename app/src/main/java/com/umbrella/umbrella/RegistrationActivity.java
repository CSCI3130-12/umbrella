package com.umbrella.umbrella;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Collection;

/**
 * Created by samdoiron on 2018-02-16.
 */

public class RegistrationActivity extends Activity {
    ViewCoursesViewModel viewModel = new ViewCoursesViewModel();

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.registration);

        ListView listView = (ListView)findViewById(R.id.course_list);

        listView.setAdapter(dataAdapter());
    }

    ArrayAdapter dataAdapter() {
        Collection<CourseListingViewModel> courses = viewModel.getCourses();
        CourseListingViewModel listings[] = new CourseListingViewModel[courses.size()];
        courses.toArray(listings);

        return new ArrayAdapter<CourseListingViewModel>(
                RegistrationActivity.this,
                android.R.layout.simple_list_item_1,
                listings
        );
    }
}
