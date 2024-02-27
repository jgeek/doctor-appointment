package com.blubank.doctorappointment.application.domain.model;

import com.blubank.doctorappointment.application.port.in.OpenTimeCommand;
import com.blubank.doctorappointment.application.port.in.OpenTimeServiceUseCase;
import com.blubank.doctorappointment.application.port.in.TakeAppointmentCommand;
import com.blubank.doctorappointment.application.port.in.TakeAppointmentUseCase;
import com.blubank.doctorappointment.application.port.out.LoadVisitTimePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.blubank.doctorappointment.application.domain.model.TestData.*;

@SpringBootTest
public class TakeAppointmentServiceTest {

    @Autowired
    private OpenTimeServiceUseCase openTimeServiceUseCase;
    @Autowired
    private TakeAppointmentUseCase takeAppointmentUseCase;
    @Autowired
    private LoadVisitTimePort loadVisitTimePort;

    private static final VisitTimeId VISIT_ID = new VisitTimeId(1L);
    private static final PatientId PATIENT_ID = PatientId.of(1L);
    private VisitTimeInfo visitTime;

    @BeforeEach
    public void init() {
        OpenTimeCommand command = new OpenTimeCommand(timeOf(openTime()), timeOf(endTime()));
        openTimeServiceUseCase.openTimePeriod(command);
        visitTime = loadVisitTimePort.loadDoctorTimes(openTime()).get(0);
    }

    @Test
    public void patient_take_an_appointment() {
        TakeAppointmentCommand takeCommand = new TakeAppointmentCommand(PATIENT, new VisitTimeId(visitTime.getId()));
        takeAppointmentUseCase.take(takeCommand);

        VisitTime visitTime = loadVisitTimePort.getById(VISIT_ID);
        Assertions.assertTrue(visitTime.isTaken());
        Assertions.assertEquals(PATIENT, visitTime.getPatient());

    }
}
