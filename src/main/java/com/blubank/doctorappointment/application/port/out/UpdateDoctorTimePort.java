package com.blubank.doctorappointment.application.port.out;


import com.blubank.doctorappointment.application.domain.model.DoctorTimes;

public interface UpdateDoctorTimePort {
    void add(DoctorTimes doctorTimes);
}
