package com.blubank.doctorappointment.application.port.in;


import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

import static com.blubank.doctorappointment.common.validation.Validation.validate;

public record DateInfo(@NotNull Integer year, @NotNull Integer month, @NotNull Integer day) {

    public DateInfo(@NotNull Integer year, @NotNull Integer month, @NotNull Integer day) {
        this.year = year;
        this.month = month;
        this.day = day;
        validate(this);
    }

    public LocalDateTime getDate() {
        return LocalDateTime.of(year, month, day, 0, 0, 0);
    }
}
