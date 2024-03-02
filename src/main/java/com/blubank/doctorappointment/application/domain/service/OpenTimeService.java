package com.blubank.doctorappointment.application.domain.service;

import com.blubank.doctorappointment.application.domain.TimeGenerator;
import com.blubank.doctorappointment.application.domain.model.VisitTime;
import com.blubank.doctorappointment.application.port.in.OpenTimeCommand;
import com.blubank.doctorappointment.application.port.in.OpenTimeServiceUseCase;
import com.blubank.doctorappointment.application.port.out.LoadVisitTimePort;
import com.blubank.doctorappointment.application.port.out.UpdateVisitTimePort;
import com.blubank.doctorappointment.common.UseCase;


import com.blubank.doctorappointment.common.dto.DateTimeDto;
import com.blubank.doctorappointment.common.exception.TimesForDayAlreadyOpened;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@UseCase
public class OpenTimeService implements OpenTimeServiceUseCase {

    private final TimeGenerator timeGenerator;
    private final UpdateVisitTimePort updateVisitTimePort;
    private final LoadVisitTimePort loadVisitTimePort;

    @Transactional
    @Override
    public void openTimePeriod(OpenTimeCommand command) {
        DateTimeDto open = command.open();
        List<VisitTime> alreadyCreatedTimes = loadVisitTimePort
                .loadDoctorTimes(LocalDateTime.of(open.getYear(), open.getMonth(), open.getDay(), 0, 0));
        if (!alreadyCreatedTimes.isEmpty()) {
            throw new TimesForDayAlreadyOpened();
        }
        var visitTimes = timeGenerator.generate(command.open().getDate(), command.end().getDate());
        updateVisitTimePort.addAll(visitTimes);
    }
}
