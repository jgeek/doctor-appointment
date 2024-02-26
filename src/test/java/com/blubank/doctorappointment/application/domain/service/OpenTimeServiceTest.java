package com.blubank.doctorappointment.application.domain.service;

import com.blubank.doctorappointment.application.port.in.OpenTimeCommand;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

public class OpenTimeServiceTest {


//    As a doctor I would like to add a start and end time for each day, so that this time
//    is broken down into 30 minutes periods. If one of the periods is becomes less than 30 minutes
//    during breakdown, then it should be ignored.

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
