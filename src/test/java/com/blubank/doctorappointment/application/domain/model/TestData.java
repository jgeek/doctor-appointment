package com.blubank.doctorappointment.application.domain.model;

import com.blubank.doctorappointment.application.port.in.OpenTimeCommand;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestData {

    public static final int Start_TIME = 10;
    public static final int END_TIME = 12;

    public static LocalDateTime openTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(Start_TIME, 0));
    }

    public static LocalDateTime endTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(END_TIME, 0));
    }

    public static OpenTimeCommand.TimeDto timeOf(LocalDateTime dateTime) {
        return new OpenTimeCommand.TimeDto(dateTime.getYear(), dateTime.getMonthValue(),
                dateTime.getDayOfMonth(), dateTime.getHour(), dateTime.getMinute());
    }
}
