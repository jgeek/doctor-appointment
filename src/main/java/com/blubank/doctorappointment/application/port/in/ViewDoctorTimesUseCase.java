package com.blubank.doctorappointment.application.port.in;

import com.blubank.doctorappointment.application.domain.model.VisitTime;
import com.blubank.doctorappointment.application.domain.model.VisitTimeInfo;

import java.util.List;

public interface ViewDoctorTimesUseCase {
    List<VisitTime> viewTimes(DoctorOpenTimesQuery query);
}
