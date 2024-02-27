package com.blubank.doctorappointment.application.domain.service;

import com.blubank.doctorappointment.application.port.in.OpenTimeCommand;
import com.blubank.doctorappointment.application.port.in.OpenTimeServiceUseCase;
import com.blubank.doctorappointment.application.port.out.UpdateVisitTimePort;
import com.blubank.doctorappointment.common.UseCase;


import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@UseCase
public class OpenTimeService implements OpenTimeServiceUseCase {


    private final TimeGenerator timeGenerator;
    private final UpdateVisitTimePort updateVisitTimePort;

    @Transactional
    @Override
    public void openTimePeriod(OpenTimeCommand command) {
        var visitTimes = timeGenerator.generate(command.open().getDate(), command.end().getDate());
        updateVisitTimePort.addAll(visitTimes);
    }
}
