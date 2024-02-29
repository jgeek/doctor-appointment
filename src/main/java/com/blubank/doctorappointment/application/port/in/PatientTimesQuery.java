package com.blubank.doctorappointment.application.port.in;

import jakarta.validation.constraints.NotNull;

import static com.blubank.doctorappointment.common.validation.Validation.validate;

public record PatientTimesQuery(@NotNull String phone) {
    public PatientTimesQuery(@NotNull String phone) {
        this.phone = phone;
        validate(this);
    }
}
