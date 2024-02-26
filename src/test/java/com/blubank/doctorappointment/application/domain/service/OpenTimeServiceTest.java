package com.blubank.doctorappointment.application.domain.service;

import com.blubank.doctorappointment.application.port.in.OpenTimeCommand;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;


public class OpenTimeServiceTest {


    private LocalDateTime now() {
        return LocalDateTime.now();
    }

    private LocalDateTime tenHoursBeforeNow() {
        return LocalDateTime.now().minusHours(10);
    }
}
