package com.blubank.doctorappointment.application.domain.service;

import com.blubank.doctorappointment.application.domain.model.VisitTime;
import com.blubank.doctorappointment.application.port.in.RemoveVisitTimeCommand;
import com.blubank.doctorappointment.application.port.in.RemoveVisitTimeUseCase;
import com.blubank.doctorappointment.application.port.out.RemoveVisitTimePort;
import com.blubank.doctorappointment.application.port.out.LoadVisitTimePort;
import com.blubank.doctorappointment.common.UseCase;
import com.blubank.doctorappointment.common.exception.NoEntityFoundException;
import com.blubank.doctorappointment.common.exception.VisitTimeIsTakenException;
import com.blubank.doctorappointment.common.exception.VisitTimeRemovedException;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class RemoveVisitTimeService implements RemoveVisitTimeUseCase {

    private RemoveVisitTimePort deleteVisitTimePort;
    private LoadVisitTimePort loadVisitTimePort;

    @Override
    public void remove(RemoveVisitTimeCommand command) {
        VisitTime visitTime;
        try {
            visitTime = loadVisitTimePort.getById(command.visitTimeId());
        } catch (NoEntityFoundException e) {
            throw new VisitTimeRemovedException(command.visitTimeId());
        }
        if (visitTime.isTaken()) {
            throw new VisitTimeIsTakenException(visitTime.getId());
        }
        deleteVisitTimePort.removeById(command.visitTimeId());
    }
}
