package com.blubank.doctorappointment.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doctor_times")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientEntity extends BaseEntity {

    private String name;
    private String phoneNumber;

}
