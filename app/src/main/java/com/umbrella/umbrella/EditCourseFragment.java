package com.umbrella.umbrella;

/**
 * Created by Ben Baker on 2018-03-23.
 */

public class EditCourseFragment {
    private static final AddDropCourseFragment ourInstance = new AddDropCourseFragment();

    public static AddDropCourseFragment getInstance() {
        return ourInstance;
    }

    private AddDropCourseFragment() {
    }
}
