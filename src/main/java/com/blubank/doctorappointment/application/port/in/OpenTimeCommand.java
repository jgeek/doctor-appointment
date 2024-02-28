package com.blubank.doctorappointment.application.port.in;

import com.blubank.doctorappointment.application.domain.service.InvalidTimeException;
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

    @Value
    public static class DateTimeDto {
        int year, month, day, hour, minute;
        LocalDateTime date;

        public DateTimeDto(int year, int month, int day, int hour, int minute) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.hour = hour;
            this.minute = minute;

            this.date = LocalDateTime.of(year, month, day, hour, minute, 0);
        }

        public boolean isAfter(DateTimeDto other) {
            return this.date.isAfter(other.getDate());
        }
    }
}
