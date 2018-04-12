package com.umbrella.umbrella;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by samdoiron on 2018-04-09.
 */

public interface StudentRepo extends Serializable {
    void updateRegistration(Student student);
    CompletableFuture<Student> getByUsername(String username);
}
