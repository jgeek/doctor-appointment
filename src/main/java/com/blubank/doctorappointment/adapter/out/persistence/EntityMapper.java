package com.blubank.doctorappointment.adapter.out.persistence;

import com.blubank.doctorappointment.application.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EntityMapper {
//    public DoctorTimesEntity mapToDoctorTimesJpaEntity(DoctorTimes doctorTimes) {
//        DoctorTimesEntity entity = new DoctorTimesEntity();
//        entity.setId(Optional.ofNullable(doctorTimes.getId()).map(DoctorTimesId::id).orElse(null));
//        entity.setOpenTime(doctorTimes.getOpenTime());
//        entity.setEndTime(doctorTimes.getEndTime());
//        List<VisitTimeEntity> visitTimeEntities = doctorTimes.getVisitTimes().stream()
//                .map(vt -> new VisitTimeEntity(vt.getStart(), vt.getEnd(), toPatentEntity(vt.getPatientId()))).toList();
//        entity.setVisitTimes(visitTimeEntities);
//        return entity;
//    }

    private PatientEntity toPatentEntity(Patient patient) {
        PatientEntity entity = new PatientEntity(patient.getName(), patient.getPhoneNumber());
        entity.setId(Optional.ofNullable(patient.getPatientId()).map(PatientId::getId).orElse(null));
        return entity;
    }

    public VisitTimeEntity mapToVisitTimeEntity(VisitTime visitTime) {
        return new VisitTimeEntity(visitTime.getStart(), visitTime.getEnd(),
                visitTime.getPatientId() != null ? visitTime.getPatientId().getId() : null);
    }

    public VisitTime mapToVisitTime(VisitTimeEntity entity) {
        return VisitTime.builder()
                .start(entity.getOpenTime())
                .end(entity.getEndTime())
                .patientId(entity.getPatientId() != null ? PatientId.of(entity.getPatientId()) : null)
                .build();
    }
}
