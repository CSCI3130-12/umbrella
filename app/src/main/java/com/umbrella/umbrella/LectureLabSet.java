package com.umbrella.umbrella;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class LectureLabSet implements Iterable<LectureLab> {
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

    public CourseSet getCourses() {
        CourseSet uniqueCourses = new CourseSet();
        for (LectureLab each : lectureLabs) {
            uniqueCourses.addCourse(each.getCourse());
        }
        return uniqueCourses;
    }

    public Iterator<LectureLab> iterator() {
        return lectureLabs.iterator();
    }


    public int getCourseCount() {
        return getCourses().size();
    }

    public boolean hasCourse(Course needle) {
        return getCourses().hasCourse(needle);
    }

    public boolean isEmpty() {
        return lectureLabs.size() == 0;
    }

    public boolean doesIntersect(LectureLabSet requiredLectures) {
        Set<LectureLab> intersection = new HashSet<>(lectureLabs);
        intersection.retainAll(requiredLectures.lectureLabs);
        return !intersection.isEmpty();
    }
}
