package com.blubank.doctorappointment.application.port.in;

import com.blubank.doctorappointment.application.domain.model.VisitTimeId;
import jakarta.validation.constraints.NotNull;

public record DeleteVisitTimeCommand(@NotNull VisitTimeId visitTimeId) {
}
