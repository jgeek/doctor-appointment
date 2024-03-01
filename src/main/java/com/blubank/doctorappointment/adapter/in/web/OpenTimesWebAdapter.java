package com.blubank.doctorappointment.adapter.in.web;

import com.blubank.doctorappointment.application.domain.model.VisitTime;
import com.blubank.doctorappointment.application.port.in.*;
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
    public String openTimes(@ModelAttribute("dates") OpenTimeCommand command, Model model) {
        openTimeServiceUseCase.openTimePeriod(command);
        return "redirect:/open-times";
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
