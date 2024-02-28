package com.blubank.doctorappointment.common.exception;

import com.blubank.doctorappointment.application.domain.model.VisitTimeId;

public class VisitTimeRemovedException extends RuntimeException {
    public VisitTimeRemovedException(VisitTimeId id) {
        super(String.format("visit time with id %s is already removed.", id.id()));
    }
}
