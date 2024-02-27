package com.blubank.doctorappointment.application.domain.model;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class VisitTimeInfo {

    Long id;
    LocalDateTime start;
    LocalDateTime end;
    Patient patient;
}
