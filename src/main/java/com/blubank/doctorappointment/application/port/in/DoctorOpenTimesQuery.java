package com.blubank.doctorappointment.application.port.in;

import java.time.LocalDateTime;

public record DoctorOpenTimesQuery(LocalDateTime date) {
}
