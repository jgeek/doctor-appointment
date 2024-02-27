package com.blubank.doctorappointment.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PatientEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
}
