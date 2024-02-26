package com.blubank.doctorappointment.adapter.out.persistence;

import com.blubank.doctorappointment.application.domain.model.VisitTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "doctor_times")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorTimesEntity extends BaseEntity {

    @Column(name = "open_time")
    private LocalDateTime openTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    @JoinColumn
    private List<VisitTimeEntity> visitTimes;
}
