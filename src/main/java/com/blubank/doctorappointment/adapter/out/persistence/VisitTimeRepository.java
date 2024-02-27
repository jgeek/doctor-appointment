package com.blubank.doctorappointment.adapter.out.persistence;

import com.blubank.doctorappointment.application.domain.model.VisitTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VisitTimeRepository extends JpaRepository<VisitTimeEntity, Long> {
    //    @Query("select t from VisitTimeEntity  t left join PatientEntity p on t.patientId = p.id  where t.openTime >= :start and t.endTime < :end")
    @Query(value = "select t.id, t.start_time, t.end_time, p.id, p.name, p.phone_number " +
            "from visit_times t left outer join patients p on t.patient_id = p.id " +
            "where t.start_time >= :start " +
            "and t.end_time < :end", nativeQuery = true)
    List<Object[]> findADayTimes(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
