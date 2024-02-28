package com.blubank.doctorappointment.common.exception;

import com.blubank.doctorappointment.application.domain.model.VisitTimeId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class VisitTimeIsTakenException extends RuntimeException {
    public VisitTimeIsTakenException(VisitTimeId visitTimeId) {
        super(String.format("visit time with id %s is taken", visitTimeId.id()));
    }
}
