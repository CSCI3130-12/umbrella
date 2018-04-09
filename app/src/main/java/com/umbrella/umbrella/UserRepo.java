package com.umbrella.umbrella;

import java.io.Serializable;
import java.util.concurrent.Future;

/**
 * Created by samdoiron on 2018-04-09.
 */

public interface UserRepo extends Serializable {
    Future<Void> updateStudentRegistration(Student student);
}
