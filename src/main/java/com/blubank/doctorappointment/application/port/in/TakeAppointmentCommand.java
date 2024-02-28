package com.blubank.doctorappointment.application.port.in;

import com.blubank.doctorappointment.application.domain.model.VisitTimeId;
import jakarta.validation.constraints.NotNull;

import static com.blubank.doctorappointment.common.validation.Validation.validate;

public record TakeAppointmentCommand(@NotNull PatientInfo patient, @NotNull VisitTimeId visitTimeId) {
    public TakeAppointmentCommand(@NotNull PatientInfo patient, @NotNull VisitTimeId visitTimeId) {
        this.patient = patient;
        this.visitTimeId = visitTimeId;
        validate(this);
    }
}
