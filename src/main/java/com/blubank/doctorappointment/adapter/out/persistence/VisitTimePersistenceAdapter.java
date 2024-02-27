package com.blubank.doctorappointment.adapter.out.persistence;

import com.blubank.doctorappointment.application.domain.model.VisitTime;
import com.blubank.doctorappointment.application.port.out.LoadVisitTimePort;
import com.blubank.doctorappointment.application.port.out.UpdateVisitTimePort;
import com.blubank.doctorappointment.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;


@PersistenceAdapter
@RequiredArgsConstructor
public class VisitTimePersistenceAdapter implements UpdateVisitTimePort, LoadVisitTimePort {

    private final VisitTimeRepository visitTimeRepository;
    private final EntityMapper mapper;

    @Override
    public void update(VisitTime visitTime) {
        VisitTimeEntity entity = mapper.mapToVisitTimeEntity(visitTime);
        visitTimeRepository.save(entity);
    }

    @Override
    public void addAll(List<VisitTime> visitTimes) {
        List<VisitTimeEntity> entities = visitTimes.stream().map(mapper::mapToVisitTimeEntity).toList();
        visitTimeRepository.saveAll(entities);
    }

    @Override
    public List<VisitTime> loadDoctorTimes() {
        List<VisitTimeEntity> entities = visitTimeRepository.findAll();
        return entities.stream().map(mapper::mapToVisitTime).toList();
    }
}
