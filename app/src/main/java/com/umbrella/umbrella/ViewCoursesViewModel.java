package com.umbrella.umbrella;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by samdoiron on 2018-02-20.
 * This is a view model containing the data necessary to display
 * a listing of courses to a user.
 */

public class ViewCoursesViewModel {
    public  ArrayList<CourseListingViewModel> courses;
    /**
     * Get the list of all course listing view models (unfiltered)
     * with a non-guaranteed order.
     *
     * @return The courses that should be displayed
     */
    ArrayList<CourseListingViewModel> getCourses(DatabaseReference db) {
        final ArrayList<CourseListingViewModel> courses = new ArrayList<>();
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                RegistrationActivity.adapter.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String name = data.child("Name").getValue(String.class);
                    RegistrationActivity.adapter.add(new CourseListingViewModel(name));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.println(Log.ERROR,"DB Error",databaseError.getMessage());
            }
        };
        db = db.child("Semester").child("Courses").child("CourseList");
        db.addValueEventListener(listener);

        courses.add(new CourseListingViewModel());

        return courses;
    }

}
