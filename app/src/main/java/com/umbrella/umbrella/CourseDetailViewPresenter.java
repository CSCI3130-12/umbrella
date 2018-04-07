package com.umbrella.umbrella;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CourseDetailViewPresenter {
    private final Course course;

    public CourseDetailViewPresenter(Course course) {
        this.course = course;
    }

    public CourseDetailViewModel getViewModel() {
        CourseDetailViewModel viewModel = new CourseDetailViewModel(
                course.getCourseID(),
                course.getCourseName(),
                course.getDescription()
        );
        viewModel.registrationOptions = makeRegistrationOptions();
        return viewModel;
    }

    private ArrayList<CourseRegistrationInfoViewModel> makeRegistrationOptions() {
        ArrayList<CourseRegistrationInfoViewModel> viewModels = new ArrayList<>();
        for (LectureLab lecture : course.getLectures()) {
            CourseRegistrationInfoViewModel viewModel = new CourseRegistrationInfoViewModel();

            viewModel.title = formatLectureOptionTitle(lecture);
            viewModels.add(viewModel);
        }
        return viewModels;
    }

    private String formatLectureOptionTitle(LectureLab lecture) {
        LectureLabTimeRange range = lecture.getTimeRange();
        String mainTitle = formatDays(lecture.getDays()) + " "
                + formatTime(range.startTime.timeOfDay)
                + " - "
                + formatTime(range.endTime.timeOfDay)
                + " (" + lecture.getStudentCount() + " / " + lecture.getMaxStudents() + ")";
        if (lecture.isFull()) {
            return mainTitle + " (" + lecture.getWaitlistCount()  + " in wait list)";
        } else {
            return mainTitle;
        }
    }

    private String formatTime(TimeOfDay time) {
        return time.hour + ":" + time.minute;
    }

    private String formatDays(List<DayOfWeek> days) {
        return days.stream().map(this::dayToLetter).collect(Collectors.joining(""));
    }

    private String dayToLetter(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY: return "M";
            case TUESDAY: return "T";
            case WEDNESDAY: return "W";
            case THURSDAY: return "R";
            case FRIDAY: return "F";
            default: return "?";
        }
    }
}

