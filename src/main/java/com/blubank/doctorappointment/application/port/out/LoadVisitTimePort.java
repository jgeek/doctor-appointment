package com.blubank.doctorappointment.application.port.out;

import com.blubank.doctorappointment.application.domain.model.VisitTime;
import com.blubank.doctorappointment.application.domain.model.VisitTimeId;
import com.blubank.doctorappointment.application.domain.model.VisitTimeInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface LoadVisitTimePort {

    List<VisitTime> loadDoctorTimes();

    List<VisitTimeInfo> loadDoctorTimes(LocalDateTime date);

    VisitTime getById(VisitTimeId visitTimeId);
}
