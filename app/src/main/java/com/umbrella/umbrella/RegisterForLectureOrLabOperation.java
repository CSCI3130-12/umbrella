package com.umbrella.umbrella;

/**
 * Created by samdoiron on 2018-03-16.
 */

class RegisterForLectureOrLabOperation implements RegistrationOperation {
    final LectureLab toRegisterFor;

    public RegisterForLectureOrLabOperation(LectureLab toRegisterFor) {
        this.toRegisterFor = toRegisterFor;
    }

    @Override
    public void perform(Student student) {
        student.registerFor(toRegisterFor);
    }
}
