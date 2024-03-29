package com.blubank.doctorappointment.application.domain.service;

import com.blubank.doctorappointment.application.domain.model.VisitTime;
import com.blubank.doctorappointment.application.port.in.OpenTimeCommand;

import static com.blubank.doctorappointment.application.domain.service.TestData.*;

import com.blubank.doctorappointment.application.port.out.LoadVisitTimePort;
import com.blubank.doctorappointment.application.port.out.UpdateVisitTimePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class OpenServiceTest {
    @Mock
    private UpdateVisitTimePort updateVisitTimePort;
    @Mock
    private LoadVisitTimePort loadVisitTimePort;
    private OpenTimeService openTimeService;

    @Captor
    private ArgumentCaptor<List<VisitTime>> argumentCaptor;

    @BeforeEach
    public void beforeEach() {
        openTimeService = new OpenTimeService(new TimeGenerator(), updateVisitTimePort, loadVisitTimePort);
    }

    @Test
    public void doctor_adds_open_times_for_a_day() {

        var openTime = openTime();
        OpenTimeCommand command = new OpenTimeCommand(timeOf(openTime), timeOf(endTime(openTime)));
        openTimeService.openTimePeriod(command);

        Mockito.verify(updateVisitTimePort).addAll(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue(), hasSize(4));
    }
}
