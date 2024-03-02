package com.blubank.doctorappointment.adapter.in.web;

import com.blubank.doctorappointment.application.domain.model.VisitTime;
import com.blubank.doctorappointment.application.port.in.*;
import com.blubank.doctorappointment.common.DateUtils;
import com.blubank.doctorappointment.common.WebAdapter;
import com.blubank.doctorappointment.common.dto.DateTimeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@WebAdapter
@RequestMapping("/clinic")
@AllArgsConstructor
public class OpenTimesWebAdapter {

    private final OpenTimeServiceUseCase openTimeServiceUseCase;
    private final ViewDoctorTimesUseCase viewDoctorTimesUseCase;

    @PostMapping("/open")
    public String openTimes(@RequestParam(value = "date") String dateStr,
                            @RequestParam(value = "startTime") String startTime,
                            @RequestParam(value = "endTime") String endTime, Model model) {
        DateTimeDto open = DateUtils.parseDate(dateStr, startTime);
        DateTimeDto end = DateUtils.parseDate(dateStr, endTime);
        var command = new OpenTimeCommand(open, end);
        openTimeServiceUseCase.openTimePeriod(command);
        return "redirect:/clinic";
    }

    private DateTimeDto getOpen() {
        return new DateTimeDto(2024, 2, 28, 10, 0);
    }

    @ModelAttribute
    public void addTimes(Model model) {
        List<VisitTime> times = viewDoctorTimesUseCase.viewTimes(new DoctorTimesQuery(getOpen()));
        model.addAttribute("times", times);
    }

    @ModelAttribute
    public void addPatientTimeRequest(Model model) {
        model.addAttribute("info", new AppointmentInfo());
    }
}
