package com.blubank.doctorappointment.common.exception;

import com.blubank.doctorappointment.application.domain.model.VisitTimeId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class VisitTimeRemovedException extends RuntimeException {
    public VisitTimeRemovedException(VisitTimeId id) {
        super(String.format("visit time with id %s is already removed.", id.id()));
    }
}
