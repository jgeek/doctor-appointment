package com.blubank.doctorappointment.application.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class AppointmentTest {


    @Test
    public void patient_take_an_appointment() {
        DoctorTimes doctorTimes = givenDoctorSetTimes();
        VisitTime visitTime = doctorTimes.getVisitTimes().get(0);

        whenPatientTakeAnAppointment(visitTime.getStart());

    }

    private void whenPatientTakeAnAppointment(LocalDateTime date) {

    }

    private DoctorTimes givenDoctorSetTimes() {
        return null;
    }
}
