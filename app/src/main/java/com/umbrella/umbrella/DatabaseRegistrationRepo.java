package com.umbrella.umbrella;

import com.google.firebase.database.DatabaseReference;

import java.util.concurrent.CompletableFuture;

/**
 * Created by samdoiron on 2018-04-10.
 */

class DatabaseRegistrationRepo implements CourseRepo {
    DatabaseUserRepo userRepo;
    String username;
    public DatabaseRegistrationRepo(String username, DatabaseReference dbReference) {
        this.username = username;
        this.userRepo = new DatabaseUserRepo(dbReference);
    }

    @Override
    public CompletableFuture<CourseSet> getAllCourses() {
        return userRepo.getByUsername(username).thenApply(Student::getRegisteredCourses);
    }

    @Override
    public CompletableFuture<Course> getCourse(String courseID) {
        return null;
    }
}
