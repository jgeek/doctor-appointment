package com.blubank.doctorappointment.application.domain.service;

import com.blubank.doctorappointment.application.domain.model.VisitTime;
import com.blubank.doctorappointment.application.port.in.DoctorTimesQuery;
import com.blubank.doctorappointment.application.port.in.ViewDoctorTimesUseCase;
import com.blubank.doctorappointment.application.port.out.LoadVisitTimePort;
import com.blubank.doctorappointment.common.UseCase;
import lombok.AllArgsConstructor;

import java.util.List;

@UseCase
@AllArgsConstructor
public class ViewDoctorTimesService implements ViewDoctorTimesUseCase {

    private final LoadVisitTimePort loadVisitTimePort;

    @Override
    public List<VisitTime> viewTimes(DoctorTimesQuery query) {
        return loadVisitTimePort.loadDoctorTimes(query.dateTime().getDate());
    }
}
