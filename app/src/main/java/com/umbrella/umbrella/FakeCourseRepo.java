package com.umbrella.umbrella;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by samdoiron on 2018-04-06.
 */

public class FakeCourseRepo implements CourseRepo, Parcelable {
    @Override
    public CourseSet getAllCourses() {
        CourseSet fakeCourses = new CourseSet();
        fakeCourses.addCourse(
                new Course("FAKE-0001", "A Fake course", "Don't register for this plz.")
        );
        return fakeCourses;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static final Parcelable.Creator<FakeCourseRepo> CREATOR
            = new Parcelable.Creator<FakeCourseRepo>() {
        public FakeCourseRepo createFromParcel(Parcel _in) {
            return new FakeCourseRepo();
        }

        public FakeCourseRepo[] newArray(int size) {
            return new FakeCourseRepo[size];
        }
    };

}
