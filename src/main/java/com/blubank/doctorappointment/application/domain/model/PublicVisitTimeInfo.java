package com.blubank.doctorappointment.application.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PublicVisitTimeInfo {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private boolean isTaken;
    public PublicVisitTimeInfo(Long id, LocalDateTime start, LocalDateTime end, boolean isTaken) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.isTaken = isTaken;
    }

}
