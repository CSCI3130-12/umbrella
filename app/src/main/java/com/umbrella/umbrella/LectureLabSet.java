package com.umbrella.umbrella;

import java.util.HashSet;
import java.util.Set;

class LectureLabSet {
    HashSet<LectureLab> lectureLabs;

    public LectureLabSet() {
        lectureLabs = new HashSet<>();
    }

    public LectureLabSet(LectureLabSet other) {
        lectureLabs = new HashSet<>();
        for (LectureLab lectureLab : other.lectureLabs) {
            lectureLabs.add(new LectureLab(lectureLab));
        }
    }

    public boolean contains(LectureLab needle) {
        return lectureLabs.contains(needle);
    }

    public int size() {
        return lectureLabs.size();
    }

    public void add(LectureLab toRegisterFor) {
        lectureLabs.add(toRegisterFor);
    }
}
