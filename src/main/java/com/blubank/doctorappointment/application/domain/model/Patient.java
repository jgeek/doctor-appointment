package com.blubank.doctorappointment.application.domain.model;


import jakarta.validation.constraints.NotNull;

import static com.blubank.doctorappointment.common.validation.Validation.validate;

public record Patient(@NotNull String name, @NotNull String phoneNumber) {

    public Patient(@NotNull String name, @NotNull String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        validate(this);
    }
}
