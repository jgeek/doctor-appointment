package com.blubank.doctorappointment.application.domain.service;

import com.blubank.doctorappointment.application.domain.model.PatientId;
import com.blubank.doctorappointment.application.domain.model.VisitTimeId;
import com.blubank.doctorappointment.application.domain.model.VisitTimeInfo;
import com.blubank.doctorappointment.application.port.in.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.blubank.doctorappointment.application.domain.model.TestData.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@SpringBootTest
public class SystemTests {

    @Autowired
    private OpenTimeServiceUseCase openTimeServiceUseCase;
    @Autowired
    private ViewDoctorTimesUseCase viewDoctorTimesUseCase;
    @Autowired
    private TakeAppointmentUseCase takeAppointmentUseCase;

    private static final VisitTimeId VISIT_ID = new VisitTimeId(1L);

    @BeforeEach
    public void beforeEach() {
        OpenTimeCommand command = new OpenTimeCommand(timeOf(openTime()), timeOf(endTime()));
        openTimeServiceUseCase.openTimePeriod(command);
    }

    @Test
    public void doctor_adds_open_times_for_a_day() {

        DoctorOpenTimesQuery viewCommand = new DoctorOpenTimesQuery(timeOf(openTime()));
        List<VisitTimeInfo> savedTimes = viewDoctorTimesUseCase.viewTimes(viewCommand);
        assertThat(savedTimes, hasSize(4));
    }

    @Test
    public void if_patient_took_a_time_doctor_should_see_name_and_phone_number() {
        TakeAppointmentCommand takeCommand = new TakeAppointmentCommand(PatientId.of(1L), VISIT_ID);
        takeAppointmentUseCase.take(takeCommand);

        DoctorOpenTimesQuery viewCommand = new DoctorOpenTimesQuery(timeOf(openTime()));
        List<VisitTimeInfo> savedTimes = viewDoctorTimesUseCase.viewTimes(viewCommand);
        Optional<VisitTimeInfo> patientTime = savedTimes.stream().filter(vt -> vt.getPatient() != null).findFirst();
        Assertions.assertTrue(patientTime.isPresent());
    }
}
