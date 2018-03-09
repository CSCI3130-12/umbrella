package com.umbrella.umbrella;

import java.util.Date;

/**
 * Created by samdoiron on 2018-03-07.
 * A fake registration info repo (storing metadata about registration.
 * Intended for use in tests / during development.
 */

public class FakeRegistrationInfoRepo implements RegistrationInfoRepo {
    private final Date deadline;

    public FakeRegistrationInfoRepo(Date deadline) {
        this.deadline = deadline;
    }

    /** Return the date when the registration period next ends */
    public Date getRegistrationDeadline() {
        return deadline;
    }
}
