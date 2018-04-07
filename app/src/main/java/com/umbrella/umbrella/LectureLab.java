package com.umbrella.umbrella;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class LectureLab {
    public enum Type {
        LECTURE,
        LAB,
        LUTORIAL
    }

    private final Course course;
    private final String instructor;
    private final String location;
    private final int maxStudents;
    private int waitlistCount;
    private int studentCount;

    private ArrayList<LectureLabTimeRange> times;

    public LectureLab(Course course, String instructor, String location, int maxStudents, int studentCount) {
        this.course = course;
        this.instructor = instructor;
        this.location = location;
        this.maxStudents = maxStudents;
        this.studentCount = studentCount;
        times = new ArrayList<>();
        waitlistCount = 0;
    }

    public LectureLab(LectureLab other) {
        this.course = new Course(other.course);
        this.instructor = other.instructor;
        this.location = other.location;
        this.maxStudents = other.maxStudents;
        this.studentCount = other.studentCount;
        this.times = other.times;
        this.waitlistCount = other.waitlistCount;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void addTimeRange(LectureLabTimeRange range) {
        times.add(range);
    }

    public Course getCourse() {
        return course;
    }

    public boolean isFull() {
        return studentCount >= maxStudents;
    }

    public void setStudentCount(int studentCount) { this.studentCount = studentCount; }

    public void setWaitlistCount(int waitlistCount) {
        this.waitlistCount = waitlistCount;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public int getWaitlistCount() {
        return waitlistCount;
    }

    public List<DayOfWeek> getDays() {
        // NOTE: Assumes that lecture starts / ends on the same day.
        return times.stream()
                .map(time -> time.startTime.day)
                .sorted()
                .collect(Collectors.toList());
    }

    public LectureLabTimeRange getTimeRange() {
        // NOTE: Assumes that lecture happen at the same time of day on all days within
        // a lecture registration option.
        return times.get(0);
    }
}

