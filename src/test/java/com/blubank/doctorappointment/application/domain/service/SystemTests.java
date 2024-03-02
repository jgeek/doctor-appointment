package com.blubank.doctorappointment.application.domain.service;

import com.blubank.doctorappointment.application.domain.model.VisitTime;
import com.blubank.doctorappointment.application.domain.model.VisitTimeId;
import com.blubank.doctorappointment.application.port.in.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.blubank.doctorappointment.application.domain.service.TestData.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SystemTests {

    @Autowired
    private OpenTimeServiceUseCase openTimeServiceUseCase;
    @Autowired
    private ViewDoctorTimesUseCase viewDoctorTimesUseCase;
    @Autowired
    private TakeAppointmentUseCase takeAppointmentUseCase;

    private static final VisitTimeId VISIT_ID = new VisitTimeId(1L);
    private LocalDateTime openTime;

    @BeforeEach
    public void beforeEach() {
        openTime = openTime();
        OpenTimeCommand command = new OpenTimeCommand(timeOf(openTime), timeOf(endTime(openTime)));
        openTimeServiceUseCase.openTimePeriod(command);
    }

    @Test
    public void doctor_adds_open_times_for_a_day() {

        DoctorTimesQuery viewCommand = new DoctorTimesQuery(timeOf(openTime));
        List<VisitTime> savedTimes = viewDoctorTimesUseCase.viewTimes(viewCommand);
        assertThat(savedTimes, hasSize(4));
    }

    @Test
    public void if_patient_took_a_time_doctor_should_see_name_and_phone_number() {
        TakeAppointmentCommand takeCommand = new TakeAppointmentCommand(PATIENT, VISIT_ID);
        takeAppointmentUseCase.take(takeCommand);

        DoctorTimesQuery viewCommand = new DoctorTimesQuery(timeOf(openTime));
        List<VisitTime> savedTimes = viewDoctorTimesUseCase.viewTimes(viewCommand);
        Optional<VisitTime> patientTime = savedTimes.stream().filter(vt -> vt.getPatient() != null).findFirst();
        Assertions.assertTrue(patientTime.isPresent());
    }
}
