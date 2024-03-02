package com.blubank.doctorappointment.adapter.in.web;

import com.blubank.doctorappointment.application.domain.model.PublicVisitTimeInfo;
import com.blubank.doctorappointment.application.port.in.*;
import com.blubank.doctorappointment.common.WebAdapter;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@WebAdapter
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientProfileWebAdapter {

    private final PatientTimesUseCase useCase;

    @GetMapping()
    public String homePage() {
        return "patient";
    }


    @GetMapping("/times")
    public String findTimes(@RequestParam(value = "phone") String phone, Model model) {

        if (StringUtils.isBlank(phone)) {
            return "patient";
        }
        PatientTimesQuery command = new PatientTimesQuery(new PhoneNumber(phone));
        List<PublicVisitTimeInfo> times = useCase.viewTimes(command);
        model.addAttribute("times", times);
        return "patient";
    }
}
