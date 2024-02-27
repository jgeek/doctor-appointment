package com.blubank.doctorappointment.application.port.out;

import com.blubank.doctorappointment.application.domain.model.VisitTime;

import java.util.List;

public interface LoadVisitTimePort {

    List<VisitTime> loadDoctorTimes();
}
