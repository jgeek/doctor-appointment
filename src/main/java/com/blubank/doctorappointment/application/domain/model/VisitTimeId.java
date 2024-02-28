package com.blubank.doctorappointment.application.domain.model;

import jakarta.validation.constraints.NotNull;

import static com.blubank.doctorappointment.common.validation.Validation.validate;

public record VisitTimeId(@NotNull Long id) {
    public VisitTimeId(@NotNull Long id) {
        this.id = id;
        validate(this);
    }
}
