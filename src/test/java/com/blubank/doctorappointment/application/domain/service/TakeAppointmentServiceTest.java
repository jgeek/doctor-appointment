package com.blubank.doctorappointment.application.domain.service;

import com.blubank.doctorappointment.application.domain.model.VisitTime;
import com.blubank.doctorappointment.application.port.in.OpenTimeCommand;
import com.blubank.doctorappointment.application.port.in.OpenTimeServiceUseCase;
import com.blubank.doctorappointment.application.port.in.TakeAppointmentCommand;
import com.blubank.doctorappointment.application.port.in.TakeAppointmentUseCase;
import com.blubank.doctorappointment.application.port.out.RemoveVisitTimePort;
import com.blubank.doctorappointment.application.port.out.LoadVisitTimePort;
import com.blubank.doctorappointment.common.exception.VisitTimeIsTakenException;
import com.blubank.doctorappointment.common.exception.VisitTimeRemovedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;

import static com.blubank.doctorappointment.application.domain.service.TestData.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TakeAppointmentServiceTest {

    @Autowired
    private OpenTimeServiceUseCase openTimeServiceUseCase;
    @Autowired
    private TakeAppointmentUseCase takeAppointmentUseCase;
    @Autowired
    private LoadVisitTimePort loadVisitTimePort;
    @Autowired
    private RemoveVisitTimePort removeVisitTimePort;
    private LocalDateTime openTime;

    @BeforeEach
    public void beforeEach() {
        removeVisitTimePort.removeAll();
        openTime = openTime();
    }

    @Test
//    @Transactional // transactional needed to rollback tx after each test run
    public void patient_take_an_appointment() {

        givenDoctorOpensSomeTimes();
        var candidateAppointment = getATimeVisit();
        whenPatientTakeAnAppointment(candidateAppointment);
        thenAppointmentShouldBeTakenByPatientSuccessfully(candidateAppointment);
    }

    @Test
//    @Transactional
    public void if_the_time_is_taken_so_patient_should_get_the_error() {
        givenDoctorOpensSomeTimes();
        VisitTime candidateAppointment = getATimeVisit();

        whenPatientTakeAnAppointment(candidateAppointment);
        Assertions.assertThrows(VisitTimeIsTakenException.class, () -> {
            whenPatientTakeAnAppointment(candidateAppointment);
        });
    }

    @Test
//    @Transactional
    public void if_the_time_is_removed_before_so_patient_should_get_the_error() {
        givenDoctorOpensSomeTimes();
        VisitTime candidateAppointment = getATimeVisit();

        givenDoctorRemoveVisitTime(candidateAppointment);

        Assertions.assertThrows(VisitTimeRemovedException.class, () -> {
            whenPatientTakeAnAppointment(candidateAppointment);
        });

    }

    private void givenDoctorRemoveVisitTime(VisitTime visitTime) {
        removeVisitTimePort.removeById(visitTime.getId());
    }

    private VisitTime getATimeVisit() {
        return loadVisitTimePort.loadDoctorTimes(openTime).get(0);
    }


    private void givenDoctorOpensSomeTimes() {
        OpenTimeCommand command = new OpenTimeCommand(timeOf(openTime), timeOf(endTime(openTime)));
        openTimeServiceUseCase.openTimePeriod(command);
    }

    private void whenPatientTakeAnAppointment(VisitTime visitTime) {
        TakeAppointmentCommand takeCommand = new TakeAppointmentCommand(PATIENT, visitTime.getId());
        takeAppointmentUseCase.take(takeCommand);
    }

    private void thenAppointmentShouldBeTakenByPatientSuccessfully(VisitTime appointment) {
        VisitTime visitTime = loadVisitTimePort.getById(appointment.getId());
        Assertions.assertTrue(visitTime.isTaken());
        Assertions.assertEquals(PATIENT.toPatient(), visitTime.getPatient());
    }
}
