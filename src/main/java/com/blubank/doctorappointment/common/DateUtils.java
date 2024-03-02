package com.blubank.doctorappointment.common;

import com.blubank.doctorappointment.application.port.in.DateInfo;
import com.blubank.doctorappointment.common.dto.DateTimeDto;

public class DateUtils {
    public static DateTimeDto parseDateTime(String dateStr, String timeStr) {
        String[] dateParts = dateStr.split("-");
        String[] timeParts = timeStr.split(":");
        return new DateTimeDto(Integer.valueOf(dateParts[0]), Integer.valueOf(dateParts[1]), Integer.valueOf(dateParts[2]),
                Integer.valueOf(timeParts[0]), Integer.valueOf(timeParts[1]));
    }

    public static DateInfo parseDate(String dateStr) {
        String[] dateParts = dateStr.split("-");
        return new DateInfo(Integer.valueOf(dateParts[0]), Integer.valueOf(dateParts[1]), Integer.valueOf(dateParts[2]));
    }
}
