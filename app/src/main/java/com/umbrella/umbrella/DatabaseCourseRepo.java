package com.umbrella.umbrella;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

/**
 * Created by justin on 08/04/18.
 * An implementation of CourseRepo that gets all the courses from the database
 */

public class DatabaseCourseRepo implements CourseRepo, Serializable {
    private final DatabaseReference lectureLabDB;
    private DatabaseReference courseDB;
    private HashMap<String, Course> courseCache;
    private HashMap<String, LectureLab> lectureLabCache;

    DatabaseCourseRepo(DatabaseReference db) {
        courseDB = db.child("Semester").child("Courses").child("CourseList");
        lectureLabDB = db.child("Semester").child("Courses").child("Lectures Labs Tutorials");
        courseCache = new HashMap<>();
        lectureLabCache = new HashMap<>();
    }

    @Override
    public CompletableFuture<CourseSet> getAllCourses() {
        CompletableFuture<CourseSet> futureCourseSet = new CompletableFuture<>();
        ArrayList<CompletableFuture<Course>> courseFutures = new ArrayList<>();
        courseDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CourseSet courses = new CourseSet();
                for (DataSnapshot courseData : dataSnapshot.getChildren()) {
                    courseFutures.add(buildCourse(courseData));
                }
                CompletableFuture.allOf(courseFutures.toArray(new CompletableFuture[courseFutures.size()]))
                        .thenApply(x -> {
                            courseFutures.stream().map(CompletableFuture::join).forEach(courses::add);
                            futureCourseSet.complete(courses);
                            return null;
                        });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return futureCourseSet;
    }

    @Override
    public CompletableFuture<Course> getCourse(String courseID) {
        if (courseCache.containsKey(courseID)) {
            return CompletableFuture.completedFuture(courseCache.get(courseID));
        }
        CompletableFuture<Course> futureCourse = new CompletableFuture<>();
        DatabaseReference data = courseDB.child(courseID);
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot courseSnapshot) {
                buildCourse(courseSnapshot).thenAccept(course -> {
                    courseCache.put(courseID, course);
                    futureCourse.complete(course);
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return futureCourse;
    }

    private CompletableFuture<Course> buildCourse(DataSnapshot courseSnapshot) {
        Course course = new Course(
                courseSnapshot.getKey(),
                courseSnapshot.child("Name").getValue(String.class),
                courseSnapshot.child("Description").getValue(String.class)
        );
        // TODO: More than lectures
        return loadCourseLectureLabs(course, courseSnapshot.child("RequiredLectureLabs").child("Lectures"))
                    .thenApply(labs -> {
                        course.setRequirements(new RequiredLectureLabs(
                                labs,
                                new LectureLabSet(),
                                new LectureLabSet()
                        ));
                        return course;
                    });
    }

    private CompletableFuture<LectureLabSet> loadCourseLectureLabs(Course course, DataSnapshot lectureLabs) {
        LectureLabSet labs = new LectureLabSet();
        ArrayList<CompletableFuture<LectureLab>> futures = new ArrayList<>();

        for (DataSnapshot lab : lectureLabs.getChildren()) {
            String crn = lab.getValue(String.class);
            futures.add(loadLectureLab(course, crn));
        }

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenApply(x -> {
                    futures.stream().map(CompletableFuture::join).forEach(labs::add);
                    return labs;
                });
    }

    private CompletableFuture<LectureLab> loadLectureLab(Course course, String crn) {
        if (lectureLabCache.containsKey(crn)) {
            return CompletableFuture.completedFuture(lectureLabCache.get(crn));
        }
        CompletableFuture<LectureLab> futureLectureLab = new CompletableFuture<>();
        DatabaseReference data = lectureLabDB.child(crn);
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot labSnapshot) {
                LectureLab result = buildLectureLab(course, labSnapshot);
                lectureLabCache.put(crn, result);
                futureLectureLab.complete(result);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return futureLectureLab;
    }

    private LectureLab buildLectureLab(Course course, DataSnapshot labSnapshot) {
            LectureLab lab = new LectureLab(
                    course,
                    labSnapshot.child("CRN").getValue(String.class),
                    labSnapshot.child("Instructor").getValue(String.class),
                    labSnapshot.child("Location").getValue(String.class),
                    labSnapshot.child("Max Students").getValue(Integer.class),
                    0
            );
            for (DataSnapshot timeData : labSnapshot.child("Time").getChildren()) {
                lab.addTimeRange(buildTimeRange(timeData));
            }
            return lab;
    }

    private LectureLabTimeRange buildTimeRange(DataSnapshot data)  {
        DayOfWeek day = parseDay(data.child("Day").getValue(String.class));
        String from = data.child("From").getValue(String.class);
        String to = data.child("To").getValue(String.class);
        return new LectureLabTimeRange(parseTime(day,from), parseTime(day, to));
    }

    private LectureLabTime parseTime(DayOfWeek day, String time) {
        String both[] = time.split(":");
        return new LectureLabTime(
                new TimeOfDay(Integer.parseInt(both[0]), Integer.parseInt(both[1])),
                day
        ) ;
    }

    private DayOfWeek parseDay(String day) {
        switch (day) {
            case "Monday":
                return DayOfWeek.MONDAY;
            case "Tuesday":
                return DayOfWeek.TUESDAY;
            case "Wednesday":
                return DayOfWeek.WEDNESDAY;
            case "Thursday":
                return DayOfWeek.THURSDAY;
            case "Friday":
                return DayOfWeek.FRIDAY;
            case "Saturday":
                return DayOfWeek.SATURDAY;
            default:
                return DayOfWeek.SUNDAY;
        }
    }
}
