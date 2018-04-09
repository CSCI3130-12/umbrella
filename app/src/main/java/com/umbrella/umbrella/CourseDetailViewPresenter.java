package com.umbrella.umbrella;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CourseDetailViewPresenter {
    private final Course course;
    private ArrayList<CourseRegistrationInfoViewModel> lectureViewModels;
    UserRepo userRepo;
    Student student;

    public void setOnViewModelChanged(Function<CourseDetailViewModel, Void> onViewModelChanged) {
        this.onViewModelChanged = onViewModelChanged;
    }

    private Function<CourseDetailViewModel, Void> onViewModelChanged;

    public CourseDetailViewPresenter(Student student, Course course, UserRepo userRepo) {
        this.student = student;
        this.course = course;
        this.userRepo = userRepo;
        this.onViewModelChanged = (_a) -> null;
        lectureViewModels = makeRegistrationOptions();
    }

    public CourseDetailViewModel getViewModel() {
        CourseDetailViewModel viewModel = new CourseDetailViewModel(
                course.getCourseID(),
                course.getCourseName(),
                course.getDescription()
        );
        viewModel.registrationOptions = lectureViewModels;
        return viewModel;
    }

    private ArrayList<CourseRegistrationInfoViewModel> makeRegistrationOptions() {
        ArrayList<CourseRegistrationInfoViewModel> viewModels = new ArrayList<>();
        for (LectureLab lecture : course.getLectures()) {
            CourseRegistrationInfoViewModel viewModel = new CourseRegistrationInfoViewModel();

            viewModel.title = formatLectureOptionTitle(lecture);
            viewModel.crn = lecture.getCRN();
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

    public void checkViewModel(int position) {
        for (CourseRegistrationInfoViewModel option : lectureViewModels) {
            option.isChecked = false;
        }
        lectureViewModels.get(position).isChecked = true;
        System.out.println("Selected is now: " + lectureViewModels.get(position).title);
    }

    public void register() {
        try {
            UpdateStudentRegistration update = new UpdateStudentRegistration(userRepo);
            update.registerStudentForLectureLabs(student, getSelectedLectureLabs());
            registrationSuccess();
        } catch (InvalidRegistrationException e) {
            registrationFailure();
        }
    }

    private void signalViewModelChanged() {
        onViewModelChanged.apply(getViewModel());
    }

    private void registrationSuccess() {
        // TODO: implement
    }

    private void registrationFailure() {
        // TODO: implement
    }

    public LectureLabSet getSelectedLectureLabs() {
        LectureLabSet lectures = course.getLectures();
        LectureLabSet selected = new LectureLabSet();
        lectureViewModels.stream()
                .map(vm -> lectures.getLectureByCRN(vm.crn))
                .forEach(selected::add);
        return selected;
    }
}

