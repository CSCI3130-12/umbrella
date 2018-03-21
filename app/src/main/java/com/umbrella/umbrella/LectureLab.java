package com.umbrella.umbrella;

import java.util.ArrayList;

class LectureLab {
    private final String instructor;
    private final String location;
    private ArrayList<LectureLabTime> times;

    public LectureLab(String instructor, String location) {
        this.instructor = instructor;
        this.location = location;
    }

    public LectureLab(LectureLab other) {
        this.instructor = other.instructor;
        this.location = other.location;
    }

    public void addTime(LectureLabTime lectureLabTime) {
        times.add(lectureLabTime);
    }
}

