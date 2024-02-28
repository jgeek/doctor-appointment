package com.blubank.doctorappointment.application.port.in;


import jakarta.validation.constraints.NotNull;

public record DoctorTimesQuery(@NotNull OpenTimeCommand.DateTimeDto dateTime) {
}
