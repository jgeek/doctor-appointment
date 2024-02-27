package com.blubank.doctorappointment.application.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Patient {

    private String name;
    private String phoneNumber;
}
