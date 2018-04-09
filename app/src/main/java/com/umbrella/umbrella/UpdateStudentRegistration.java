package com.umbrella.umbrella;

import java.util.concurrent.Future;

/**
 * Created by samdoiron on 2018-04-09.
 */

public class UpdateStudentRegistration {
    UserRepo repo;

    public UpdateStudentRegistration(UserRepo repo) {
        this.repo = repo;
    }

    public Future<Void> registerStudentForLectureLabs(Student student, LectureLabSet labs) throws InvalidRegistrationException {
        RegistrationTransaction transaction = new RegistrationTransaction(student);
        for (LectureLab lab : labs) {
            transaction.addOperation(new RegisterForLectureOrLabOperation(lab));
        }

        if (!transaction.isValid()) {
            throw new InvalidRegistrationException();
        }

        return repo.updateStudentRegistration(transaction.commit());
    }
}
