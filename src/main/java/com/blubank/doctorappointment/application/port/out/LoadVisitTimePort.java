package com.blubank.doctorappointment.application.port.out;

import com.blubank.doctorappointment.application.domain.model.PublicVisitTimeInfo;
import com.blubank.doctorappointment.application.domain.model.VisitTime;
import com.blubank.doctorappointment.application.domain.model.VisitTimeId;

import java.time.LocalDateTime;
import java.util.List;

public interface LoadVisitTimePort {

    List<VisitTime> loadDoctorTimes(LocalDateTime date);

    VisitTime getById(VisitTimeId visitTimeId);

    List<PublicVisitTimeInfo> loadTimesForPatients(LocalDateTime date);
}
