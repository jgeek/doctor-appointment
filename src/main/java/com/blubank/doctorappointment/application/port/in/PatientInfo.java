package com.blubank.doctorappointment.application.port.in;

import com.blubank.doctorappointment.application.domain.model.Patient;
import jakarta.validation.constraints.NotNull;

import static com.blubank.doctorappointment.common.validation.Validation.validate;

public record PatientInfo(@NotNull String name, @NotNull String phoneNumber) {
    public PatientInfo(@NotNull String name, @NotNull String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        validate(this);
    }

    public Patient toPatient() {
        return new Patient(name, phoneNumber);
    }
}
