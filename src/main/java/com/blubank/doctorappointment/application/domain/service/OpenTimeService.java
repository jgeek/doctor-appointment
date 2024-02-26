package com.blubank.doctorappointment.application.domain.service;

import com.blubank.doctorappointment.application.domain.model.DoctorTimes;
import com.blubank.doctorappointment.application.port.in.OpenTimeCommand;
import com.blubank.doctorappointment.application.port.in.OpenTimeServiceUserCase;
import com.blubank.doctorappointment.application.port.out.UpdateDoctorTimePort;
import com.blubank.doctorappointment.common.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
@Transactional
public class OpenTimeService implements OpenTimeServiceUserCase {

    private final UpdateDoctorTimePort updateVisitTimePort;

    @Override
    public void openTimePeriod(OpenTimeCommand command) {
        DoctorTimes doctorTimes = DoctorTimes.builder()
                .openTime(command.open())
                .endTime(command.end())
                .build();
        doctorTimes.generateTimes();
        updateVisitTimePort.add(doctorTimes);
    }
}
