package com.blubank.doctorappointment.application.domain.model;


import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class VisitTime {
    private VisitTimeId id;
    private LocalDateTime start;
    private LocalDateTime end;
    private PatientId patientId;
    private Long version;

    public boolean isTaken() {
        return patientId != null;
    }

    public void takeBy(PatientId patientId) {
        this.patientId = patientId;
    }
}
