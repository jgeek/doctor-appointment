package com.blubank.doctorappointment.adapter.out.persistence;

import com.blubank.doctorappointment.application.domain.model.*;
import com.blubank.doctorappointment.application.port.out.RemoveVisitTimePort;
import com.blubank.doctorappointment.application.port.out.LoadVisitTimePort;
import com.blubank.doctorappointment.application.port.out.UpdateVisitTimePort;
import com.blubank.doctorappointment.common.PersistenceAdapter;
import com.blubank.doctorappointment.common.exception.NoEntityFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@PersistenceAdapter
@RequiredArgsConstructor
public class VisitTimePersistenceAdapter implements UpdateVisitTimePort, LoadVisitTimePort, RemoveVisitTimePort {

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
    public List<VisitTime> loadDoctorTimes(LocalDateTime date) {
        List<Object[]> entities = visitTimeRepository.findADayTimes(date, date.plusDays(1));
        return entities.stream()
                .map(this::toVisitTime)
                .toList();
    }

    @Override
    public List<PublicVisitTimeInfo> loadTimesForPatients(LocalDateTime date) {
        return loadDoctorTimes(date).stream()/*.filter(t -> !t.isTaken())*/
                .map(t -> new PublicVisitTimeInfo(t.getId().id(), t.getStart(), t.getEnd(), t.isTaken())).toList();
    }

    @Override
    public List<PublicVisitTimeInfo> loadPatientTimes(String phoneNumber) {
        List<PublicVisitTimeInfo> times = visitTimeRepository.findPatientTimes(phoneNumber);
        return times.stream().map(t -> new PublicVisitTimeInfo(t.getId(), t.getStart(), t.getEnd(), true)).toList();
    }


    private VisitTime toVisitTime(Object[] arr) {
        return VisitTime.builder()
                .id(toVisitId(arr[0]))
                .start(toLocalDateTime(arr[1]))
                .end(toLocalDateTime(arr[2]))
                .patient(new Patient((String) arr[3], (String) arr[4]))
                .build();
    }

    private VisitTimeId toVisitId(Object idObj) {
        return new VisitTimeId(Long.valueOf(idObj.toString()));
    }

    @Override
    public VisitTime getById(VisitTimeId visitTimeId) {
        return visitTimeRepository.findById(visitTimeId.id())
                .map(mapper::mapToVisitTime).orElseThrow(() -> new NoEntityFoundException("visit time not found"));
    }


    private LocalDateTime toLocalDateTime(Object object) {
        return ((Timestamp) object).toLocalDateTime();
    }

    @Override
    public void removeById(VisitTimeId visitTimeId) {
        if (notExist(visitTimeId)) {
            throw new NoEntityFoundException("visit time not exist");
        }
        visitTimeRepository.deleteById(visitTimeId.id());
    }

    private boolean notExist(VisitTimeId visitTimeId) {
        try {
            visitTimeRepository.getReferenceById(visitTimeId.id());
            return false;
        } catch (EntityNotFoundException e) {
            return true;
        }
    }
}
