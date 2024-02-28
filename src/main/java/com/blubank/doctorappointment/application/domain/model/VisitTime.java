package com.blubank.doctorappointment.application.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class VisitTime {
    private VisitTimeId id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Patient patient;
    @JsonIgnore
    private Long version;

    public boolean isTaken() {
        return patient != null;
    }

    public void takeBy(Patient patient) {
        this.patient = patient;
    }
}
