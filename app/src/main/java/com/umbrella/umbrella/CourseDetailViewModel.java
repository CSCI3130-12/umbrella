package com.umbrella.umbrella;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by wauch on 2018-03-16.
 */

public class CourseDetailViewModel {
    public String id;
    public String name;
    public String description;
    public ArrayList<CourseRegistrationInfoViewModel> registrationOptions;

    public String successMessage;
    public String errorMessage;

    CourseDetailViewModel(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        registrationOptions = new ArrayList<>();
    }
}
