package com.blubank.doctorappointment.adapter.out.persistence;

import com.blubank.doctorappointment.application.domain.model.PatientInfo;
import com.blubank.doctorappointment.application.domain.model.VisitTime;
import com.blubank.doctorappointment.application.domain.model.VisitTimeId;
import com.blubank.doctorappointment.application.domain.model.VisitTimeInfo;
import com.blubank.doctorappointment.application.port.out.LoadVisitTimePort;
import com.blubank.doctorappointment.application.port.out.UpdateVisitTimePort;
import com.blubank.doctorappointment.common.PersistenceAdapter;
import com.blubank.doctorappointment.common.exception.NoEntityFoundException;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    @Override
    public List<VisitTimeInfo> loadDoctorTimes(LocalDateTime date) {
        List<Object[]> entities = visitTimeRepository.findADayTimes(date, date.plusDays(1));
        List<VisitTimeInfo> list = entities.stream()
                .map(o -> new VisitTimeInfo(Long.valueOf(o[0].toString()), toLocalDateTime(o[1]), toLocalDateTime(o[2]),
                        new PatientInfo((Long) o[3], (String) o[4], (String) o[5])))
                .toList();
        return list;
//        return mapper.mapToVisitTimeEntities(entities);
    }

    @Override
    public VisitTime getById(VisitTimeId visitTimeId) {
        return visitTimeRepository.findById(visitTimeId.id())
                .map(mapper::mapToVisitTime).orElseThrow(() -> new NoEntityFoundException("visit time not found"));
    }

    private LocalDateTime toLocalDateTime(Object object) {
        return ((Timestamp) object).toLocalDateTime();
    }
}
