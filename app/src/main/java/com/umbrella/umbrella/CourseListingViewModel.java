package com.umbrella.umbrella;

/**
 * Created by samdoiron on 2018-02-20.
 *
 * A course listing is a single list-item that represents a course.
 * It is intended to be used as the data source for a list of courses
 * (eg. in RegistrationView).
 */

public class CourseListingViewModel {
    public final String name;
    public final String id;

    CourseListingViewModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /** @return the displayable title of the course */
    @Override
    public String toString() {
        return name;
    }
}
