package com.blubank.doctorappointment.application.port.in;

import com.blubank.doctorappointment.application.domain.model.PublicVisitTimeInfo;

import java.util.List;

public interface PatientSearchForVisitTimeUseCase {
    List<PublicVisitTimeInfo> searchTimes(TimesViewQuery timesViewQuery);
}
