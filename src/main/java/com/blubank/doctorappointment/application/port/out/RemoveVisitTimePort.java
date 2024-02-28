package com.blubank.doctorappointment.application.port.out;

import com.blubank.doctorappointment.application.domain.model.VisitTimeId;

public interface RemoveVisitTimePort {
    void removeById(VisitTimeId visitTimeId);
}
