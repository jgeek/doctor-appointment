package com.blubank.doctorappointment.application.port.in;


import com.blubank.doctorappointment.common.dto.DateTimeDto;
import jakarta.validation.constraints.NotNull;

public record DoctorTimesQuery(@NotNull DateTimeDto dateTime) {
}
