package com.blubank.doctorappointment.application.port.in;

import com.blubank.doctorappointment.application.domain.model.VisitTime;

import java.util.List;

public interface ViewDoctorTimesUseCase {
    List<VisitTime> viewTimes(DoctorTimesQuery query);
}
