package com.blubank.doctorappointment.application.domain.service;

import com.blubank.doctorappointment.application.port.in.DeleteVisitTimeCommand;
import com.blubank.doctorappointment.application.port.in.DeleteVisitTimeUseCase;
import com.blubank.doctorappointment.application.port.out.DeleteVisitTimePort;
import com.blubank.doctorappointment.application.port.out.LoadVisitTimePort;
import com.blubank.doctorappointment.common.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class DeleteVisitTimeService implements DeleteVisitTimeUseCase {

    private DeleteVisitTimePort deleteVisitTimePort;
    private LoadVisitTimePort loadVisitTimePort;

    @Override
    public void delete(DeleteVisitTimeCommand command) {
        loadVisitTimePort.getById(command.visitTimeId());
        deleteVisitTimePort.removeById(command.visitTimeId());
    }
}
