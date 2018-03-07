package com.umbrella.umbrella;

import java.util.Date;

/**
 * Created by samdoiron on 2018-03-07.
 */

public class FakeRegistrationInfoRepo implements RegistrationInfoRepo {
    private final Date deadline;

    public FakeRegistrationInfoRepo(Date deadline) {
        this.deadline = deadline;
    }
    public Date getRegistrationDeadline() {
        return deadline;
    }
}
