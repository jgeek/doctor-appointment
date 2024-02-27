package com.blubank.doctorappointment.adapter.out.persistence;

import com.blubank.doctorappointment.application.domain.model.PatientId;
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
    @Column(name = "start_time")
    private LocalDateTime openTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "patient_id")
    private Long patientId;
}
