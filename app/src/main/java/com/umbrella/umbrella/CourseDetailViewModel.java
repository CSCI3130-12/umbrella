package com.umbrella.umbrella;

import android.app.Application;

/**
 * Created by wauch on 2018-03-16.
 */

public class CourseDetailViewModel extends Application{

    Course course;
    MyApplicationData appState;
    public CourseDetailViewModel() {
        appState = new MyApplicationData("Course");
        course = new Course("2000", "Example Course Name", "This course has a great example description");
    }


}
