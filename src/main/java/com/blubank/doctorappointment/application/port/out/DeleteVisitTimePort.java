package com.blubank.doctorappointment.application.port.out;

import com.blubank.doctorappointment.application.domain.model.VisitTimeId;

public interface DeleteVisitTimePort {
    void removeById(VisitTimeId visitTimeId);
}
