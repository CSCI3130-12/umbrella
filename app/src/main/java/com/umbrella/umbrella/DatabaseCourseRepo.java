package com.umbrella.umbrella;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by justin on 08/04/18.
 * An implementation of CourseRepo that gets all the courses from the database
 */

public class DatabaseCourseRepo implements CourseRepo {

    private CourseSet courses;


    /**
     * Gets the CourseSet of all courses in the repo
     * @return A CourseSet containing the courses
     */
    @Override
    public CourseSet getAllCourses(){
        return courses;
    }

    /**
     * Gets a specific course from the repo
     * @param s The ID of the course to search for
     * @return The course with the given ID or Null if not found
     */
    @Override
    public Course getCourse(String s){
        return courses.getCourseByID(s);
    }

    /**
     * A constructor to initialise the repo with the info from the database
     * @param db The Firebase database reference
     */
    public DatabaseCourseRepo(DatabaseReference db){

        courses = new CourseSet();
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                RegistrationActivity.adapter.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String name = data.child("Name").getValue(String.class);
                    String crn = "crn";//data.getValue(String.class);
                    String id = data.child("CourseID").getValue(String.class);
                    Course course = new Course(crn,id,name);
                    courses.addCourse(course);
                }
                RegistrationActivity.presenter.pushToAdapter(RegistrationActivity.adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.println(Log.ERROR,"DB Error",databaseError.getMessage());
            }
        };
        db = db.child("Semester").child("Courses").child("CourseList");
        db.addValueEventListener(listener);

    }
}
