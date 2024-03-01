package com.blubank.doctorappointment.common.dto;

import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;

@Data
public class DateTimeDto {
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

    public DateTimeDto() {

    }

    public boolean isAfter(DateTimeDto other) {
        return this.date.isAfter(other.getDate());
    }
}