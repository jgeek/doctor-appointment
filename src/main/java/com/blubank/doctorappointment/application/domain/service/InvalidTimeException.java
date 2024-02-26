package com.blubank.doctorappointment.application.domain.service;

public class InvalidTimeException extends RuntimeException {
    public InvalidTimeException() {
        super("open time should be before the end time");
    }
}
