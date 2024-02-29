package com.blubank.doctorappointment.application.domain.model;

import java.time.LocalDateTime;

public record PublicVisitTimeInfo(Long id, LocalDateTime start, LocalDateTime end, boolean isTaken) {
}
