package com.blubank.doctorappointment.common.mapper;


import com.blubank.doctorappointment.application.domain.model.VisitTime;
import com.blubank.doctorappointment.common.dto.DateTimeDto;
import com.blubank.doctorappointment.common.dto.PublicVisitTimeInfoDto;
import org.mapstruct.Mapper;

import java.time.LocalDate;

//@Mapper
public interface VisitTimeInfoMapper {
    PublicVisitTimeInfoDto toPublicVisitTimeInfo(VisitTime info);

}
