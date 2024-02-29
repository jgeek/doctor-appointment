package com.blubank.doctorappointment.application.port.in;

import com.blubank.doctorappointment.application.domain.model.PublicVisitTimeInfo;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface PatientTimesUseCase {
    List<PublicVisitTimeInfo> viewTimes(@NotNull PatientTimesQuery query);
}
