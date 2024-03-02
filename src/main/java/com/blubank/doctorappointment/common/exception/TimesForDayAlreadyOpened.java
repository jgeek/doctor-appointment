package com.blubank.doctorappointment.common.exception;

public class TimesForDayAlreadyOpened extends ClinicException {
    public TimesForDayAlreadyOpened() {
        super("times already opened for this day!");
    }
}
