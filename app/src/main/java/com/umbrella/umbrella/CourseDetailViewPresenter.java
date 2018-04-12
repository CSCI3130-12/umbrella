package com.umbrella.umbrella;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CourseDetailViewPresenter {
    private final Course course;
    private ArrayList<CourseRegistrationInfoViewModel> lectureViewModels;
    StudentRepo studentRepo;
    CompletableFuture<Student> student;
    private String errorFlash;
    private String successFlash;

    public void setOnViewModelChanged(Function<CourseDetailViewModel, Void> onViewModelChanged) {
        this.onViewModelChanged = onViewModelChanged;
    }

    private Function<CourseDetailViewModel, Void> onViewModelChanged;

    public CourseDetailViewPresenter(ActiveUser userInfo, Course course, StudentRepo studentRepo) {
        this.course = course;
        this.studentRepo = studentRepo;
        this.onViewModelChanged = (_a) -> null;
        lectureViewModels = makeRegistrationOptions();
        this.student = studentRepo.getByUsername(userInfo.getUsername()).thenApply(student -> {
            signalViewModelChanged();
            return student;
        });
    }

    public CourseDetailViewModel getViewModel() {
        CourseDetailViewModel viewModel = new CourseDetailViewModel(
                course.getCourseID(),
                course.getCourseName(),
                course.getDescription()
        );
        viewModel.registrationOptions = lectureViewModels;
        viewModel.errorMessage = errorFlash;
        viewModel.successMessage = successFlash;

        successFlash = null;
        errorFlash = null;
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
    }

    public CompletableFuture<Void> register() {
        System.out.println("Waiting");
        CompletableFuture<Void> done = new CompletableFuture<>();
        student.thenAccept(student -> {
            System.out.println("Got student");
            try {
                System.out.println("In success");
                UpdateStudentRegistration update = new UpdateStudentRegistration(studentRepo);
                update.registerStudentForLectureLabs(student, getSelectedLectureLabs());
                registrationSuccess();
                System.out.println("End");
            } catch (InvalidRegistrationException e) {
                registrationFailure();
            } catch (Exception e) {
                e.printStackTrace();
                registrationFailure();
            } finally {
                done.complete(null);
            }
        });
        return done;
    }

    private void signalViewModelChanged() {
        onViewModelChanged.apply(getViewModel());
    }

    private void registrationSuccess() {
        successFlash = "Registration success!";
        System.out.println("success");
        signalViewModelChanged();
    }

    private void registrationFailure() {
        errorFlash = "Invalid registration";
        System.out.println("failure");
        signalViewModelChanged();
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

