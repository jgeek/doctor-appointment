package com.blubank.doctorappointment.common.mapper;

import com.blubank.doctorappointment.common.dto.DateTimeDto;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

//@Mapper
public interface DateMapper {

    DateTimeDto toDate(LocalDateTime dto);

}
