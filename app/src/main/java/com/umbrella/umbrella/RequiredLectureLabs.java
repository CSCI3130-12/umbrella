package com.umbrella.umbrella;

/**
 * Created by samdoiron on 2018-03-23.
 */

public class RequiredLectureLabs {
    LectureLabSet requiredLectures;
    LectureLabSet requiredLabs;
    LectureLabSet requiredTutorials;

    public RequiredLectureLabs() {
        this.requiredLectures = new LectureLabSet();
        this.requiredLabs = new LectureLabSet();
        this.requiredTutorials = new LectureLabSet();
    }

    public RequiredLectureLabs(LectureLabSet requiredLectures, LectureLabSet requiredLabs, LectureLabSet requiredTutorials) {
        this.requiredLectures = requiredLectures;
        this.requiredLabs = requiredLabs;
        this.requiredTutorials = requiredTutorials;
    }

    public RequiredLectureLabs(RequiredLectureLabs other) {
        this.requiredLectures = other.requiredLectures;
        this.requiredLabs = other.requiredLabs;
        this.requiredTutorials = other.requiredTutorials;
    }

    public boolean isMetBy(LectureLabSet registration) {
        return (requiredLectures.isEmpty() || registration.doesIntersect(requiredLectures))
                && (requiredTutorials.isEmpty() || registration.doesIntersect(requiredTutorials))
                && requiredLabs.isEmpty() || registration.doesIntersect(requiredLabs);
    }

    public void addRequiredLabOption(LectureLab lectureLab) {
        requiredLabs.add(lectureLab);
    }
}
