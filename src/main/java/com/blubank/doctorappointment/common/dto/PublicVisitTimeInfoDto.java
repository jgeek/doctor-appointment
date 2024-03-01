package com.blubank.doctorappointment.common.dto;

import lombok.Data;

@Data
public class PublicVisitTimeInfoDto {
    private Long id;
    private DateTimeDto start;
    private DateTimeDto end;
    private boolean isTaken;

}
