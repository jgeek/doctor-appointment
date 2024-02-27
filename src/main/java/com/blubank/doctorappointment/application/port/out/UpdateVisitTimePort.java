package com.blubank.doctorappointment.application.port.out;


import com.blubank.doctorappointment.application.domain.model.VisitTime;

import java.util.List;

public interface UpdateVisitTimePort {
    void update(VisitTime visitTime);

    void addAll(List<VisitTime> visitTimes);
}
