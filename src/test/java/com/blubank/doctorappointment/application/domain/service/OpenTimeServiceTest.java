package com.blubank.doctorappointment.application.domain.service;

import com.blubank.doctorappointment.application.port.in.OpenTimeCommand;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

public class OpenTimeServiceTest {

//    1. If doctor enters an end date that is sooner than start date, appropriate error should be shown
//2. If doctor enters start and end date so that the period is less than 30 minutes then no time
//    should be added.

    private final OpenTimeService openTimeService = new OpenTimeService();

    @Test
    public void if_end_date_is_sooner_than_start_date_we_should_get_error() {
        OpenTimeCommand command = new OpenTimeCommand(now(), tenHoursBeforeNow());
        openTimeService.openTimePeriod(command);
    }

    private LocalDateTime now() {
        return LocalDateTime.now();
    }

    private LocalDateTime tenHoursBeforeNow() {
        return LocalDateTime.now().minusHours(10);
    }
}
