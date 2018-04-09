package com.umbrella.umbrella;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by samdoiron on 2018-04-09.
 */

public class InMemoryUserRepo implements UserRepo {
    // Maps Username -> Registration
    private HashMap<String, LectureLabSet> registrations;

    public InMemoryUserRepo() {
        registrations = new HashMap<>();
    }

    @Override
    public Future<Void> updateStudentRegistration(Student student) {
        LectureLabSet storedRegistration = new LectureLabSet(student.getRegistration());
        registrations.put(student.getUsername(), storedRegistration);
        CompletableFuture<Void> future = new CompletableFuture<>();
        future.complete(null);
        return future;
    }
}
