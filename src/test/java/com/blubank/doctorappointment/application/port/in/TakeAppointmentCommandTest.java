package com.blubank.doctorappointment.application.port.in;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.blubank.doctorappointment.application.domain.model.TestData.*;

public class TakeAppointmentCommandTest {
    @Test
    public void validation_ok() {
        new TakeAppointmentCommand(PATIENT, VISIT_TIME_ID);
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
            new TakeAppointmentCommand(new PatientInfo("behnia", null), VISIT_TIME_ID);
        });
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            new TakeAppointmentCommand(new PatientInfo(null, "09122341499"), VISIT_TIME_ID);
        });
    }
}
