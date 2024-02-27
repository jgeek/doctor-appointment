package com.blubank.doctorappointment.application.port.in;

import com.blubank.doctorappointment.application.domain.model.PatientId;
import com.blubank.doctorappointment.application.domain.model.VisitTimeId;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TakeAppointmentCommand {
    private @NotNull PatientId patientId;
    private @NotNull VisitTimeId visitTimeId;
}
