package com.blubank.doctorappointment.application.port.in;


import jakarta.validation.constraints.NotNull;

public record DoctorOpenTimesQuery(@NotNull OpenTimeCommand.TimeDto dateTime) {
}
