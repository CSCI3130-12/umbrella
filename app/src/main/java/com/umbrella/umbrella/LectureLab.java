package com.umbrella.umbrella;

import java.util.ArrayList;
import java.util.Iterator;

class LectureLab {
    private final Course course;
    private final String instructor;
    private final String location;
    private final int maxStudents;
    private int studentCount;
    private ArrayList<LectureLabTime> times;

    public LectureLab(Course course, String instructor, String location, int maxStudents, int studentCount) {
        this.course = course;
        this.instructor = instructor;
        this.location = location;
        this.maxStudents = maxStudents;
        this.studentCount = studentCount;
    }

    public LectureLab(LectureLab other) {
        this.course = new Course(other.course);
        this.instructor = other.instructor;
        this.location = other.location;
        this.maxStudents = other.maxStudents;
        this.studentCount = other.studentCount;
    }

    public void addTime(LectureLabTime lectureLabTime) {
        times.add(lectureLabTime);
    }

    public Course getCourse() {
        return course;
    }

    public boolean isFull() {
        return studentCount >= maxStudents;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getMaxStudents() {
        return maxStudents;
    }
}

