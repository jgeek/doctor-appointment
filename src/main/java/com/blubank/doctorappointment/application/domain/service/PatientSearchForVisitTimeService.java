package com.blubank.doctorappointment.application.domain.service;

import com.blubank.doctorappointment.application.domain.model.PublicVisitTimeInfo;
import com.blubank.doctorappointment.application.port.in.PatientSearchForVisitTimeUseCase;
import com.blubank.doctorappointment.application.port.in.TimesViewQuery;
import com.blubank.doctorappointment.application.port.out.LoadVisitTimePort;
import com.blubank.doctorappointment.common.UseCase;
import lombok.AllArgsConstructor;

import java.util.List;

@UseCase
@AllArgsConstructor
public class PatientSearchForVisitTimeService implements PatientSearchForVisitTimeUseCase {

    private LoadVisitTimePort loadVisitTimePort;

    @Override
    public List<PublicVisitTimeInfo> searchTimes(TimesViewQuery timesViewQuery) {
        return loadVisitTimePort.loadTimesForPatients(timesViewQuery.dateInfo().getDate());
    }
}
