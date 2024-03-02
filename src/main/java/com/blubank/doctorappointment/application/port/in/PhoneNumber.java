package com.blubank.doctorappointment.application.port.in;

import jakarta.validation.constraints.NotBlank;

import static com.blubank.doctorappointment.common.validation.Validation.validate;

public record PhoneNumber(@NotBlank String number) {
    public PhoneNumber(@NotBlank String number) {
        this.number = number;
        validate(this);
    }
}
