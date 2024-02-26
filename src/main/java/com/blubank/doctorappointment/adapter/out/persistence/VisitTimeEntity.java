package com.blubank.doctorappointment.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "visit_times")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitTimeEntity extends BaseEntity {
    @Column(name = "open_time")
    private LocalDateTime openTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;
}
