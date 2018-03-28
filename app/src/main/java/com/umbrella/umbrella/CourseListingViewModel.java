package com.umbrella.umbrella;

/**
 * Created by samdoiron on 2018-02-20.
 *
 * A course listing is a single list-item that represents a course.
 * It is intended to be used as the data source for a list of courses
 * (eg. in RegistrationView).
 */

public class CourseListingViewModel {


    /** @return the displayable title of the course */
    @Override
    public String toString() {
        return getName();
    }

    /** @return the displayable title of the course */
    public String getName() {
        return "Some course name";
    }
}
