package com.umbrella.umbrella;

import android.app.Application;

/**
 * Created by wauch on 2018-03-16.
 */

public class CourseDetailViewModel {
    public final String id;
    public final String name;
    public final String description;

    CourseDetailViewModel(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
