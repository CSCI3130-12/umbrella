package com.umbrella.umbrella;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by samdoiron on 2018-02-16.
 * This is the activity that shows a user a listing of courses that they
 * can register for.
 */

public class RegistrationActivity extends Activity {

    public static ViewCoursesPresenter presenter;
    ApplicationData appData;
    public static ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        appData = (ApplicationData)getApplicationContext();
        CourseRepo repo = new DatabaseCourseRepo(appData.dbReference);
        presenter = new ViewCoursesPresenter(repo);
        presenter.refreshData();

        setContentView(R.layout.registration);


        ListView listView = (ListView) findViewById(R.id.course_list);

        adapter = dataAdapter();
        listView.setAdapter(adapter);
    }


    /**
     * Adapts the course data provided by the view model into a form that the
     * android list view wants.
     *
     * @return an ArrayAdapter that can be used with a list view.
     */
    ArrayAdapter dataAdapter() {

        ArrayList<CourseListingViewModel> listings = presenter.getViewModel().courses;

        return new ArrayAdapter<>(
                RegistrationActivity.this,
                android.R.layout.simple_list_item_1,
                listings
        );
    }
}
