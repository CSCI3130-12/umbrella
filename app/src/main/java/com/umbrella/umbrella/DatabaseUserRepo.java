package com.umbrella.umbrella;

import android.text.method.SingleLineTransformationMethod;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

/**
 * Created by samdoiron on 2018-04-09.
 */

class DatabaseUserRepo implements StudentRepo {
    private final DatabaseCourseRepo courseRepo;
    private DatabaseReference lectureLabDB;
    private DatabaseReference courseDB;
    private DatabaseReference studentDB;

    public DatabaseUserRepo(DatabaseReference dbReference) {
        studentDB = dbReference.child("Users").child("Student");
        lectureLabDB = dbReference.child("Semester").child("Courses").child("Lectures Labs Tutorials");
        courseRepo = new DatabaseCourseRepo(dbReference);
    }

    @Override
    public void updateRegistration(Student student) {
        try {
            ArrayList<String> newLabCRNs = new ArrayList<>();
            for (LectureLab lab : student.getRegistration()) {
                newLabCRNs.add(lab.getCRN());
            }
            HashMap<String, Object> update = new HashMap<>();
            update.put("LectureLabSet", newLabCRNs);
            studentDB.child(student.getUsername()).updateChildren(update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CompletableFuture<Student> getByUsername(String username) {
        CompletableFuture<Student> futureStudent = new CompletableFuture<>();
        studentDB.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try  {
                    buildStudent(dataSnapshot).thenAccept(student -> {
                        System.out.println("Built student " + student.getUsername());
                        futureStudent.complete(student);
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return futureStudent;
    }

    private CompletableFuture<Student> buildStudent(DataSnapshot studentSnapshot) {
        try {
            DataSnapshot coursesData = studentSnapshot.child("CourseSet");
            DataSnapshot lectureLabsData = studentSnapshot.child("LectureLabSet");
            CompletableFuture<CourseSet> courseSetFuture = loadStudentCourses(coursesData);
            courseSetFuture.thenAccept(f -> {
                System.out.println("got course set");
            });
            CompletableFuture<LectureLabSet> lectureLabSetFuture = loadStudentLectureLabs(lectureLabsData);
            lectureLabSetFuture.thenAccept(f -> {
                System.out.println("got lecture lab set");
            });
            System.out.println("waiting for both");
            return CompletableFuture.allOf(courseSetFuture, lectureLabSetFuture)
                    .thenApply(x -> {
                        System.out.println("loaded student dependants");
                        LectureLabSet lectureLabs = lectureLabSetFuture.join();
                        CourseSet courseSet = courseSetFuture.join();
                        return new Student(
                                studentSnapshot.child("Name").getValue(String.class),
                                studentSnapshot.child("Password").getValue(String.class),
                                courseSet,
                                lectureLabs
                        );
                    });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private CompletableFuture<LectureLabSet> loadStudentLectureLabs(DataSnapshot lectureLabs) {
        LectureLabSet labs = new LectureLabSet();
        ArrayList<CompletableFuture<LectureLab>> futures = new ArrayList<>();

        for (DataSnapshot course : lectureLabs.getChildren()) {
            String crn = course.getValue(String.class);
            System.out.println("ADD");
            CompletableFuture<LectureLab> future = loadLectureLab(crn);
            future.thenAccept(x -> System.out.println("RESOLVE"));
            futures.add(future);
        }

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenApply(x -> {
                    System.out.println("load student lecture labs done");
                    // Bug: Never reached
                    futures.stream().map(CompletableFuture::join).forEach(labs::add);
                    return labs;
                });
    }

    private CompletableFuture<LectureLab> loadLectureLab(String crn) {
        CompletableFuture<LectureLab> futureLectureLab = new CompletableFuture<>();
        DatabaseReference data = lectureLabDB.child(crn);
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot labSnapshot) {
                System.out.println("Loaded a lecture lab");
                buildLectureLab(labSnapshot).thenAccept(lectureLab -> {
                    System.out.println("Built the lecture lab for " + lectureLab.getCourse().getCourseName());
                    futureLectureLab.complete(lectureLab);
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return futureLectureLab;
    }

    private CompletableFuture<LectureLab> buildLectureLab(DataSnapshot labSnapshot) {
        return courseRepo.getCourse(labSnapshot.child("CourseID").getValue(String.class)).thenApply(course -> {
            System.out.println("Got course for lecture lab");
            try {
                return new LectureLab(
                        course,
                        labSnapshot.child("CRN").getValue(String.class),
                        labSnapshot.child("Instructor").getValue(String.class),
                        labSnapshot.child("Location").getValue(String.class),
                        labSnapshot.child("Max Students").getValue(Integer.class),
                        0
                );
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    private CompletableFuture<CourseSet> loadStudentCourses(DataSnapshot courses) {
        CourseSet courseSet = new CourseSet();
        ArrayList<CompletableFuture<Course>> futures = new ArrayList<>();

        for (DataSnapshot course : courses.getChildren()) {
            String courseID = course.getValue(String.class);
            futures.add(courseRepo.getCourse(courseID));
        }

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenApply(x -> {
                    futures.stream().map(CompletableFuture::join).forEach(courseSet::add);
                    return courseSet;
                });
    }

}
