package com.blubank.doctorappointment.adapter.in.web;

import com.blubank.doctorappointment.application.domain.model.VisitTime;
import com.blubank.doctorappointment.application.port.in.DoctorTimesQuery;
import com.blubank.doctorappointment.application.port.in.OpenTimeCommand;
import com.blubank.doctorappointment.application.port.in.OpenTimeServiceUseCase;
import com.blubank.doctorappointment.application.port.in.ViewDoctorTimesUseCase;
import com.blubank.doctorappointment.common.WebAdapter;
import com.blubank.doctorappointment.common.dto.DateTimeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@WebAdapter
@RequestMapping("/clinic")
@AllArgsConstructor
public class ShowTimesToDoctorWebAdapter {

    private final OpenTimeServiceUseCase openTimeServiceUseCase;
    private final ViewDoctorTimesUseCase viewDoctorTimesUseCase;
    @GetMapping()
    public String homePage() {
        return "doctor-times";
    }

    @GetMapping("/times")
    public String getTimes(Model model) {
        viewDoctorTimesUseCase.viewTimes(new DoctorTimesQuery(getOpen()));
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
        model.addAttribute("info", new DateTimeDto());
    }
}
