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
    private Patient patient;
    private Long version;

    public boolean isTaken() {
        return patient != null;
    }

    public void takeBy(Patient patient) {
        this.patient = patient;
    }
}
