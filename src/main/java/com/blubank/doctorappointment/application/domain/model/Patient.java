package com.blubank.doctorappointment.application.domain.model;


import lombok.Data;

@Data
public class Patient {

    PatientId patientId;
    private String name;
    private String phoneNumber;
}
