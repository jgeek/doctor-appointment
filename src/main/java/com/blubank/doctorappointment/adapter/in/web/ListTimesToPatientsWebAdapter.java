package com.blubank.doctorappointment.adapter.in.web;

import com.blubank.doctorappointment.application.domain.model.PublicVisitTimeInfo;
import com.blubank.doctorappointment.application.port.in.*;
import com.blubank.doctorappointment.common.WebAdapter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@WebAdapter
@RequestMapping("/times")
@AllArgsConstructor
public class ListTimesToPatientsWebAdapter {

    private final PatientSearchForVisitTimeUseCase useCase;

    @GetMapping()
    public String homePage(@Valid String test, Model model, RedirectAttributes redirectAttributes) {

//        ShowTimesToDoctorWebAdapter.handleErrors(model);
        return "times";
//        return view;
    }

    @ModelAttribute
    public void addTimes(Model model) {
        LocalDateTime date = LocalDateTime.of(2024, 2, 28, 0, 0);
        List<PublicVisitTimeInfo> times = useCase.searchTimes(new TimesViewQuery(DateInfo.fromDate(date)));
        model.addAttribute("times", times);
    }

    @ModelAttribute
    public void addPatientTimeRequest(Model model) {
        model.addAttribute("info", new AppointmentInfo());
    }
}
