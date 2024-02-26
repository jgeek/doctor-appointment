package com.blubank.doctorappointment.application.port.in;

import com.blubank.doctorappointment.application.domain.service.InvalidTimeException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class OpenTimeCommandTest {

    @Test
    public void validation_ok() {
        new OpenTimeCommand(LocalDateTime.now().minusHours(10), LocalDateTime.now());
        // no exception
    }

    @Test
    public void null_date_fails() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            new OpenTimeCommand(null, LocalDateTime.now());
        });
    }

    @Test
    public void open_time_should_be_before_end_time() {
        Assertions.assertThrows(InvalidTimeException.class, () -> {
            new OpenTimeCommand(LocalDateTime.now(), LocalDateTime.now().minusHours(1));
        });
    }
}
