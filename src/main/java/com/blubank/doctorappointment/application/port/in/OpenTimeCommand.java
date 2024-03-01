package com.blubank.doctorappointment.application.port.in;

import com.blubank.doctorappointment.application.domain.service.InvalidTimeException;
import com.blubank.doctorappointment.common.dto.DateTimeDto;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.LocalDateTime;

import static com.blubank.doctorappointment.common.validation.Validation.validate;

public record OpenTimeCommand(@NotNull DateTimeDto open,
                              @NotNull DateTimeDto end) {

    public OpenTimeCommand(DateTimeDto open, DateTimeDto end) {
        this.open = open;
        this.end = end;
        validate(this);
        if (open.isAfter(end)) {
            throw new InvalidTimeException();
        }
    }
}
