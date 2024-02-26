package com.blubank.doctorappointment.application.port.in;

import com.blubank.doctorappointment.application.domain.model.Doctor;
import com.blubank.doctorappointment.application.domain.service.InvalidTimeException;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

import static com.blubank.doctorappointment.common.validation.Validation.validate;

public record OpenTimeCommand(@NotNull LocalDateTime open,
                              @NotNull LocalDateTime end) {

    public OpenTimeCommand(LocalDateTime open, LocalDateTime end) {
        this.open = open;
        this.end = end;
        validate(this);
        if (open.isAfter(end)) {
            throw new InvalidTimeException();
        }
    }
}
