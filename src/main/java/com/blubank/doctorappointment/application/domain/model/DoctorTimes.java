package com.blubank.doctorappointment.application.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.LongStream;


@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DoctorTimes {
    public static final Long VISIT_PERIOD_MINUTES = 30L;

    private final LocalDateTime openTime;
    private final LocalDateTime endTime;

    public List<VisitTime> generateTimes() {
        var visitCount = (int) Duration.between(openTime, endTime).toMinutes() / VISIT_PERIOD_MINUTES;
        List<VisitTime> times = LongStream.rangeClosed(1, visitCount)
                .mapToObj(i -> VisitTime.builder().start(openTime.plusMinutes(VISIT_PERIOD_MINUTES * (i - 1)))
                        .end(openTime.plusMinutes(VISIT_PERIOD_MINUTES * i))
                        .build())
                .toList();
        return times;
    }
}
