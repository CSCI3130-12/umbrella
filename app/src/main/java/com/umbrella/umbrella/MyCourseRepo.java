package com.umbrella.umbrella;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by justin on 09/04/18.
 * An implementation of the CourseRepo interface for a specific User's courses
 */

public class MyCourseRepo {
    private CourseSet courses;

    /**
     * Gets all the courses in the repo
     * @return The CourseSet containing all the courses in the repo
     */
    public CourseSet getAllCourses(){
        return courses;
    }

    public Course getCourse(String s){
        return courses.getCourseByID(s);
    }

    /**
     * Initialise a new course repo from the database
     * @param db A reference to the Firebase database
     * @param studentID The student to create a repo for
     */
    public MyCourseRepo(DatabaseReference db, final String studentID){
        courses = new CourseSet();
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MyCourseFragment.adapter.clear();
                DataSnapshot userCourses = dataSnapshot.child("Users").child("Student").child(studentID).child("CourseSet");
                for (DataSnapshot data : userCourses.getChildren()) {
                    String courseID = data.getValue(String.class);
                    DataSnapshot courseData = dataSnapshot.child("Semester").child("Courses").child("CourseList").child(courseID);

                    //TODO: Update to work with Sam's new Course class
                    String name = courseData.child("Name").getValue(String.class);
                    String crn = "crn";//data.getValue(String.class);
                    String id = courseData.child("CourseID").getValue(String.class);

                    Course course = new Course(crn,id,name);
                    courses.addCourse(course);
                }
                MyCourseFragment.presenter.pushToAdapter(MyCourseFragment.adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.println(Log.ERROR,"DB Error",databaseError.getMessage());
            }
        };
        db.addValueEventListener(listener);
    }
}
