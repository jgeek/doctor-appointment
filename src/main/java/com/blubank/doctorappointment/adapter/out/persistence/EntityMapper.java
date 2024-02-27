package com.blubank.doctorappointment.adapter.out.persistence;

import com.blubank.doctorappointment.application.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.List;

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
        return new PatientEntity(patient.getName(), patient.getPhoneNumber());
    }

    public VisitTimeEntity mapToVisitTimeEntity(VisitTime visitTime) {
        VisitTimeEntity entity = new VisitTimeEntity(visitTime.getStart(), visitTime.getEnd(),
                toPatientEntity(visitTime.getPatient()), visitTime.getVersion());
        entity.setId(visitTime.getId() != null ? visitTime.getId().id() : null);
        return entity;
    }

    private PatientEntity toPatientEntity(Patient patient) {
        return patient != null ? new PatientEntity(patient.getName(), patient.getPhoneNumber()) : null;
    }

    private Long nullSave(VisitTimeId id) {
        return id != null ? id.id() : null;
    }

    public VisitTime mapToVisitTime(VisitTimeEntity entity) {
        return VisitTime.builder()
                .id(new VisitTimeId(entity.getId()))
                .start(entity.getOpenTime())
                .end(entity.getEndTime())
                .patient(toPatient(entity.getPatient()))
                .version(entity.getVersion())
                .build();
    }

    private Patient toPatient(PatientEntity patient) {
        return patient != null ? new Patient(patient.getName(), patient.getPhoneNumber()) : null;
    }

    public List<VisitTime> mapToVisitTimeEntities(List<VisitTimeEntity> entities) {
        return entities.stream().map(this::mapToVisitTime).toList();
    }
}
