package com.blubank.doctorappointment.common.exception;

import com.blubank.doctorappointment.application.domain.model.VisitTimeId;

public class VisitTimeIsTakenException extends RuntimeException {
    public VisitTimeIsTakenException(VisitTimeId visitTimeId) {
        super(String.format("visit time with id %s is taken", visitTimeId.id()));
    }
}
