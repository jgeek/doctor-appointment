package com.blubank.doctorappointment.common.exception;

public class InvalidTimeException extends RuntimeException {
    public InvalidTimeException() {
        super("open time should be before the end time");
    }
}
