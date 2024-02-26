package com.blubank.doctorappointment.application.domain.model;

import com.blubank.doctorappointment.application.domain.service.OpenTimeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.blubank.doctorappointment.application.domain.model.DoctorTimes.VISIT_PERIOD_MINUTES;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;


public class DoctorTimesTest {

    private static final int Start_TIME = 10;
    private static final int END_TIME = 12;

    @Test
    public void should_split_times_correctly() {
        DoctorTimes doctorTimes = DoctorTimes.builder()
                .openTime(openTime())
                .endTime(endTime())
                .build();
        doctorTimes.generateTimes();
        List<VisitTime> times = doctorTimes.getVisitTimes();
        assertThat(times, hasSize(4));

        var firstVisitTime = times.get(0);
        Assertions.assertEquals(firstVisitTime.getStart().getHour(), 10);
        assertThat(firstVisitTime.getEnd().getMinute(), equalTo(VISIT_PERIOD_MINUTES.intValue()));

        for (VisitTime vt : times) {
            Assertions.assertEquals(Duration.between(vt.getStart(), vt.getEnd()), Duration.ofMinutes(VISIT_PERIOD_MINUTES));
        }
    }

    @Test
    public void should_split_times_correctly_if_has_less_than_30_minutes_part() {
        DoctorTimes doctorTimes = DoctorTimes.builder()
                .openTime(openTime())
                .endTime(endTime().plusMinutes(10))
                .build();
        doctorTimes.generateTimes();
        List<VisitTime> times = doctorTimes.getVisitTimes();
        assertThat(times, hasSize(4));
    }

    @Test
    public void should_not_generate_visit_time_if_period_is_less_than_30_minutes() {
        DoctorTimes doctorTimes = DoctorTimes.builder()
                .openTime(openTime())
                .endTime(openTime().plusMinutes(29))
                .build();
        doctorTimes.generateTimes();
        List<VisitTime> times = doctorTimes.getVisitTimes();
        assertThat(times, hasSize(0));
    }

    private LocalDateTime openTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(Start_TIME, 0));
    }

    private LocalDateTime endTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(END_TIME, 0));
    }
}