package com.blubank.doctorappointment.common.exception;

import com.blubank.doctorappointment.application.domain.model.VisitTime;
import com.blubank.doctorappointment.application.domain.model.VisitTimeId;

public class VisitTimeIsTaken extends RuntimeException {
    public VisitTimeIsTaken(VisitTime visitTime) {
        super(String.format("visit time with id %s is taken", visitTime.getId().id()));
    }
}
