package com.umbrella.umbrella;

import java.util.Date;

/**
 * Created by samdoiron on 2018-03-07.
 * A repository that contains metadata about registration
 * (such as deadlines)
 */

public interface RegistrationInfoRepo {
    /** @return  the next deadline for registration */
    public Date getRegistrationDeadline();
}
