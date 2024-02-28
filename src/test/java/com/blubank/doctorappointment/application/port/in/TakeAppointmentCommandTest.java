package com.blubank.doctorappointment.application.port.in;

import com.blubank.doctorappointment.application.domain.model.Patient;
import com.blubank.doctorappointment.application.domain.model.VisitTimeId;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.blubank.doctorappointment.application.domain.model.TestData.PATIENT;
import static com.blubank.doctorappointment.application.domain.model.TestData.timeOf;

public class TakeAppointmentCommandTest {

    private static final VisitTimeId VISIT_TIME_ID = new VisitTimeId(1L);

    @Test
    public void validation_ok() {
        new TakeAppointmentCommand(new Patient("behnia", "09122341499"), VISIT_TIME_ID);
        // no exception
    }

    @Test
    public void null_values_fails() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            new TakeAppointmentCommand(null, VISIT_TIME_ID);
        });
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            new TakeAppointmentCommand(PATIENT, null);
        });
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            new TakeAppointmentCommand(new Patient("behnia", null), VISIT_TIME_ID);
        });
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            new TakeAppointmentCommand(new Patient(null, "09122341499"), VISIT_TIME_ID);
        });
    }
}
