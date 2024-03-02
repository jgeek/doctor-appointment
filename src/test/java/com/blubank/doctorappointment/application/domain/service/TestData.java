package com.blubank.doctorappointment.application.domain.service;

import com.blubank.doctorappointment.application.domain.model.VisitTimeId;
import com.blubank.doctorappointment.application.port.in.PatientInfo;
import com.blubank.doctorappointment.common.dto.DateTimeDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

public class TestData {

    public static final int Start_TIME = 10;
    public static final int END_TIME = 12;
    public static final PatientInfo PATIENT = new PatientInfo("behnia", "09121234567");
    public static final VisitTimeId VISIT_TIME_ID = new VisitTimeId(1L);


    public static LocalDateTime openTime() {
        int day = new Random().nextInt(365);
        return LocalDateTime.of(LocalDate.ofYearDay(2024, day), LocalTime.of(Start_TIME, 0));
    }

    public static LocalDateTime endTime(LocalDateTime openTime) {
        return LocalDateTime.of(openTime.toLocalDate(), LocalTime.of(END_TIME, 0));
    }

    public static DateTimeDto timeOf(LocalDateTime dateTime) {
        return new DateTimeDto(dateTime.getYear(), dateTime.getMonthValue(),
                dateTime.getDayOfMonth(), dateTime.getHour(), dateTime.getMinute());
    }
}
