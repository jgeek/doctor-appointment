package com.blubank.doctorappointment.application.domain;

import com.blubank.doctorappointment.application.domain.model.VisitTime;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.LongStream;

import static com.blubank.doctorappointment.config.AppConfig.VISIT_PERIOD_MINUTES;

@Component
public class TimeGenerator {
    public List<VisitTime> generate(LocalDateTime openTime, LocalDateTime endTime) {
        var visitCount = (int) Duration.between(openTime, endTime).toMinutes() / VISIT_PERIOD_MINUTES;
        return LongStream.rangeClosed(1, visitCount)
                .mapToObj(i -> VisitTime.builder()
                        .start(openTime.plusMinutes(VISIT_PERIOD_MINUTES * (i - 1)))
                        .end(openTime.plusMinutes(VISIT_PERIOD_MINUTES * i))
                        .build()
                )
                .toList();
    }
}
