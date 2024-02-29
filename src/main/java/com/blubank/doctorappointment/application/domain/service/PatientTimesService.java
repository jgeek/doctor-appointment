package com.blubank.doctorappointment.application.domain.service;

import com.blubank.doctorappointment.application.domain.model.PublicVisitTimeInfo;
import com.blubank.doctorappointment.application.port.in.PatientTimesQuery;
import com.blubank.doctorappointment.application.port.in.PatientTimesUseCase;
import com.blubank.doctorappointment.application.port.out.LoadVisitTimePort;
import com.blubank.doctorappointment.common.UseCase;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.util.List;

@UseCase
@AllArgsConstructor
public class PatientTimesService implements PatientTimesUseCase {

    private final LoadVisitTimePort loadVisitTimePort;

    @Override
    public List<PublicVisitTimeInfo> viewTimes(@NotNull PatientTimesQuery query) {
        return loadVisitTimePort.loadPatientTimes(query.phone());
    }
}
