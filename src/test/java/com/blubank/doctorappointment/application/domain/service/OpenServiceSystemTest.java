package com.blubank.doctorappointment.application.domain.service;

import com.blubank.doctorappointment.application.domain.model.VisitTime;
import com.blubank.doctorappointment.application.domain.model.VisitTimeInfo;
import com.blubank.doctorappointment.application.port.in.DoctorOpenTimesQuery;
import com.blubank.doctorappointment.application.port.in.OpenTimeCommand;
import com.blubank.doctorappointment.application.port.in.OpenTimeServiceUseCase;
import com.blubank.doctorappointment.application.port.in.ViewDoctorTimesUseCase;
import com.blubank.doctorappointment.application.port.out.LoadVisitTimePort;
import com.blubank.doctorappointment.application.port.out.UpdateVisitTimePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.blubank.doctorappointment.application.domain.model.TestData.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@SpringBootTest
public class OpenServiceSystemTest {

    @Autowired
    private OpenTimeServiceUseCase openTimeServiceUseCase;
    @Autowired
    private ViewDoctorTimesUseCase viewDoctorTimesUseCase;

    @Test
    public void doctor_adds_open_times_for_a_day() {

        OpenTimeCommand command = new OpenTimeCommand(timeOf(openTime()), timeOf(endTime()));
        openTimeServiceUseCase.openTimePeriod(command);

        DoctorOpenTimesQuery viewCommand = new DoctorOpenTimesQuery(timeOf(openTime()));
        List<VisitTimeInfo> savedTimes = viewDoctorTimesUseCase.viewTimes(viewCommand);
        assertThat(savedTimes, hasSize(4));
    }
}
