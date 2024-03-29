package com.blubank.doctorappointment.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "visit_times")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitTimeEntity extends BaseEntity {
    @Column(name = "start_time")
    private LocalDateTime openTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Embedded
    private PatientEntity patient;

    @Version
    private Long version;
}
