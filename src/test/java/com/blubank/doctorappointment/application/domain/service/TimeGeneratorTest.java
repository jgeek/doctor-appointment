package com.blubank.doctorappointment.application.domain.service;

import static com.blubank.doctorappointment.application.domain.service.TestData.*;

import com.blubank.doctorappointment.application.domain.model.VisitTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static com.blubank.doctorappointment.config.AppConfig.VISIT_PERIOD_MINUTES;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;


public class TimeGeneratorTest {

    private static final int Start_TIME = 10;
    private static final int END_TIME = 12;

    private final TimeGenerator timeGenerator = new TimeGenerator();

    @Test
    public void should_split_times_correctly() {

        var openTime = openTime();
        List<VisitTime> times = timeGenerator.generate(openTime, endTime(openTime));
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
        var openTime = openTime();
        List<VisitTime> times = timeGenerator.generate(openTime, endTime(openTime).plusMinutes(10));
        assertThat(times, hasSize(4));
    }

    @Test
    public void should_not_generate_visit_time_if_period_is_less_than_30_minutes() {
        var openTime = openTime();
        List<VisitTime> times = timeGenerator.generate(openTime, openTime.plusMinutes(29));
        assertThat(times, hasSize(0));
    }
}