package com.blubank.doctorappointment.application.domain.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
public class VisitTime {
    VisitTimeId id;
    LocalDateTime start;
    LocalDateTime end;
    PatientId patientId;

    public boolean isTaken() {
        return patientId != null;
    }
}
