package com.blubank.doctorappointment.adapter.out.persistence;

import com.blubank.doctorappointment.application.domain.model.DoctorTimes;
import com.blubank.doctorappointment.application.domain.model.DoctorTimesId;
import com.blubank.doctorappointment.application.domain.model.Patient;
import com.blubank.doctorappointment.application.domain.model.PatientId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EntityMapper {
    public DoctorTimesEntity mapToDoctorTimesJpaEntity(DoctorTimes doctorTimes) {
        DoctorTimesEntity entity = new DoctorTimesEntity();
        entity.setId(Optional.ofNullable(doctorTimes.getId()).map(DoctorTimesId::id).orElse(null));
        entity.setOpenTime(doctorTimes.getOpenTime());
        entity.setEndTime(doctorTimes.getEndTime());
        List<VisitTimeEntity> visitTimeEntities = doctorTimes.getVisitTimes().stream()
                .map(vt -> new VisitTimeEntity(vt.getStart(), vt.getEnd(), toPatentEntity(vt.getPatient()))).toList();
        entity.setVisitTimes(visitTimeEntities);
        return entity;
    }

    private PatientEntity toPatentEntity(Patient patient) {
        PatientEntity entity = new PatientEntity(patient.getName(), patient.getPhoneNumber());
        entity.setId(Optional.ofNullable(patient.getPatientId()).map(PatientId::id).orElse(null));
        return entity;
    }
}
