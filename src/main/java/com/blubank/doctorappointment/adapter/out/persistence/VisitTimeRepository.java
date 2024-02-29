package com.blubank.doctorappointment.adapter.out.persistence;

import com.blubank.doctorappointment.application.domain.model.PublicVisitTimeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitTimeRepository extends JpaRepository<VisitTimeEntity, Long> {
    @Query(value = "select t.id, t.start_time, t.end_time, t.name, t.phone_number " +
            "from visit_times t " +
            "where t.start_time >= :start " +
            "and t.end_time < :end", nativeQuery = true)
    List<Object[]> findADayTimes(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("select v from VisitTimeEntity v where v.patient.phoneNumber = :phoneNumber")
    List<PublicVisitTimeInfo> findPatientTimes(@Param("phoneNumber") String phoneNumber);
}
