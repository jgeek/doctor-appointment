package com.blubank.doctorappointment.application.domain.model;

import lombok.Data;

import java.util.Date;

@Data
public class VisitTimeInfo {
    private Long id;
    private Date start;
    private Date end;
    private boolean isTaken;

    public VisitTimeInfo(Long id, Date start, Date end, boolean isTaken) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.isTaken = isTaken;
    }
}
