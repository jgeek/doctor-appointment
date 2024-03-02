package com.blubank.doctorappointment.application.port.in;

import jakarta.validation.constraints.NotNull;

import static com.blubank.doctorappointment.common.validation.Validation.validate;

public record PatientTimesQuery(PhoneNumber phone) {
    public PatientTimesQuery(@NotNull PhoneNumber phone) {
        this.phone = phone;
        validate(this);
    }
}
