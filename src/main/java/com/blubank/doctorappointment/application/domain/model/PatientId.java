package com.blubank.doctorappointment.application.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PatientId {
    Long id;

    public static PatientId of(Long id) {
        return new PatientId(id);
    }
}
