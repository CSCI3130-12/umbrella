package com.umbrella.umbrella;

import java.io.Serializable;

/**
 * Created by samdoiron on 2018-04-06.
 */

public class FakeCourseRepo implements CourseRepo, Serializable {

    private final CourseSet fakeCourses;

    public FakeCourseRepo() {
        fakeCourses = new CourseSet();
        fakeCourses.addCourse(
                makeFakeCourse()
        );
    }

    @Override
    public CourseSet getAllCourses() {
        return fakeCourses;
    }

    @Override
    public Course getCourse(String courseID) {
        return fakeCourses.getCourseByID(courseID);
    }

    private Course makeFakeCourse() {
        Course fakeCourse= new Course(
                "FAKE-0001",
                "A Fake Course",
                "Test course, please ignore."
        );
        RequiredLectureLabs requirements = new RequiredLectureLabs();
        requirements.addRequiredLectureOption(makeFakeLecture(fakeCourse));
        requirements.addRequiredLectureOption(makeOtherFakeLecture(fakeCourse));
        fakeCourse.setRequirements(requirements);
        return fakeCourse;
    }

    private LectureLab makeFakeLecture(Course course) {
        LectureLab lecture = new LectureLab(
                course,
                "Dr. Fake",
                "The Moon",
                35,
                35
        );
        lecture.setWaitlistCount(5);
        lecture.addTimeRange(new LectureLabTimeRange(
                new LectureLabTime(new TimeOfDay(10, 35), DayOfWeek.WEDNESDAY),
                new LectureLabTime(new TimeOfDay(11, 25), DayOfWeek.WEDNESDAY)
        ));
        lecture.addTimeRange(new LectureLabTimeRange(
                new LectureLabTime(new TimeOfDay(10, 35), DayOfWeek.FRIDAY),
                new LectureLabTime(new TimeOfDay(11, 25), DayOfWeek.FRIDAY)
        ));
        lecture.addTimeRange(new LectureLabTimeRange(
                new LectureLabTime(new TimeOfDay(10, 35), DayOfWeek.MONDAY),
                new LectureLabTime(new TimeOfDay(11, 25), DayOfWeek.MONDAY)
        ));
        return lecture;
    }

    private LectureLab makeOtherFakeLecture(Course course) {
        LectureLab lecture = new LectureLab(
                course,
                "Dr. Fake",
                "The Moon",
                35,
                35
        );
        lecture.setWaitlistCount(5);
        lecture.addTimeRange(new LectureLabTimeRange(
                new LectureLabTime(new TimeOfDay(10, 35), DayOfWeek.TUESDAY),
                new LectureLabTime(new TimeOfDay(11, 25), DayOfWeek.TUESDAY)
        ));
        lecture.addTimeRange(new LectureLabTimeRange(
                new LectureLabTime(new TimeOfDay(10, 35), DayOfWeek.THURSDAY),
                new LectureLabTime(new TimeOfDay(11, 25), DayOfWeek.THURSDAY)
        ));
        return lecture;
    }
}
