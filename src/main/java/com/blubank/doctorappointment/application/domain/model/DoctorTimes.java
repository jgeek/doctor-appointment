package com.blubank.doctorappointment.application.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.LongStream;


@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DoctorTimes {

    @Getter
    private final DoctorTimesId id;

    @NotNull
    @Getter
    private final LocalDateTime openTime;
    @NotNull
    @Getter
    private final LocalDateTime endTime;
    @Getter
    private List<VisitTime> visitTimes;

//    public void generateTimes() {
//        var visitCount = (int) Duration.between(openTime, endTime).toMinutes() / VISIT_PERIOD_MINUTES;
//        this.visitTimes = LongStream.rangeClosed(1, visitCount)
//                .mapToObj(i -> VisitTime.builder()
//                        .start(openTime.plusMinutes(VISIT_PERIOD_MINUTES * (i - 1)))
//                        .end(openTime.plusMinutes(VISIT_PERIOD_MINUTES * i))
//                        .build()
//                )
//                .toList();
//    }
}
