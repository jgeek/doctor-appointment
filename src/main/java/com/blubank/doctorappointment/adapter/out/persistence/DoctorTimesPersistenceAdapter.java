package com.blubank.doctorappointment.adapter.out.persistence;

import com.blubank.doctorappointment.application.domain.model.DoctorTimes;
import com.blubank.doctorappointment.application.port.out.UpdateDoctorTimePort;
import com.blubank.doctorappointment.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;


@PersistenceAdapter
@RequiredArgsConstructor
public class DoctorTimesPersistenceAdapter implements UpdateDoctorTimePort {

    private final DoctorTimesRepository doctorTimesRepository;
    private final EntityMapper mapper;

    @Override
    public void add(DoctorTimes doctorTimes) {
        DoctorTimesEntity entity = mapper.mapToDoctorTimesJpaEntity(doctorTimes);
        doctorTimesRepository.save(entity);
    }
}
