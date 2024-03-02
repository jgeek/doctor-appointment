package com.blubank.doctorappointment.application.domain.service;

import com.blubank.doctorappointment.application.domain.model.PublicVisitTimeInfo;
import com.blubank.doctorappointment.application.port.in.PatientTimesQuery;
import com.blubank.doctorappointment.application.port.in.PhoneNumber;
import com.blubank.doctorappointment.application.port.out.LoadVisitTimePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.blubank.doctorappointment.application.domain.service.TestData.openTime;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PatientTimesServiceTest {

    @Mock
    private LoadVisitTimePort loadVisitTimePort;
    @InjectMocks
    private PatientTimesService patientTimesService;

    @Test
    public void patient_should_see_his_times_providing_phone_number() {
        var openTime = openTime();
        PublicVisitTimeInfo timeInfo = new PublicVisitTimeInfo(1L, openTime, openTime.plusMinutes(30), true);
        String phoneNumber = "123";
        given(loadVisitTimePort.loadPatientTimes(eq(phoneNumber)))
                .willReturn(List.of(timeInfo));
        PatientTimesQuery command = new PatientTimesQuery(new PhoneNumber(phoneNumber));
        List<PublicVisitTimeInfo> times = patientTimesService.viewTimes(command);
        Assertions.assertEquals(1, times.size());
        Assertions.assertEquals(List.of(timeInfo), times);
    }
}
