package com.blubank.doctorappointment.application.domain.service;

import com.blubank.doctorappointment.application.domain.model.VisitTime;
import com.blubank.doctorappointment.application.port.in.TakeAppointmentCommand;
import com.blubank.doctorappointment.application.port.in.TakeAppointmentUseCase;
import com.blubank.doctorappointment.application.port.out.LoadVisitTimePort;
import com.blubank.doctorappointment.application.port.out.UpdateVisitTimePort;
import com.blubank.doctorappointment.common.UseCase;
import com.blubank.doctorappointment.common.exception.VisitTimeIsTaken;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@AllArgsConstructor
@Slf4j
public class TakeAppointmentService implements TakeAppointmentUseCase {

    private LoadVisitTimePort loadVisitTimePort;
    private UpdateVisitTimePort updateVisitTimePort;

    @Transactional
    @Override
    public void take(TakeAppointmentCommand command) {

        VisitTime visitTime = loadVisitTimePort.getById(command.getVisitTimeId());
        if (visitTime.isTaken()) {
            throw new VisitTimeIsTaken(visitTime);
        }
        log.info("patient with number {} is taking the appointment {}", command.getPatient().getPhoneNumber(), visitTime);
        visitTime.takeBy(command.getPatient());
        updateVisitTimePort.update(visitTime);
        log.info("patient with number {} took the appointment {}", command.getPatient().getPhoneNumber(), visitTime);
    }
}
