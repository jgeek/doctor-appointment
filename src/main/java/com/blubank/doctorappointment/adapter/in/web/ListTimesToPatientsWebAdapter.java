package com.blubank.doctorappointment.adapter.in.web;

import com.blubank.doctorappointment.application.domain.model.PublicVisitTimeInfo;
import com.blubank.doctorappointment.application.port.in.*;
import com.blubank.doctorappointment.common.DateUtils;
import com.blubank.doctorappointment.common.WebAdapter;
import com.blubank.doctorappointment.common.dto.DateTimeDto;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@WebAdapter
@RequestMapping("/times")
@AllArgsConstructor
public class ListTimesToPatientsWebAdapter {

    private final PatientSearchForVisitTimeUseCase useCase;

    @GetMapping()
    public String homePage() {

//        ShowTimesToDoctorWebAdapter.handleErrors(model);
        return "times";
//        return view;
    }

    @ModelAttribute
    public void addTimes(@RequestParam(value = "date", required = false) String dateStr, Model model) {
        DateInfo date;
        if (StringUtils.isNotBlank(dateStr)) {
            date = DateUtils.parseDate(dateStr);
        } else {
            date = DateInfo.fromDate(LocalDateTime.now());
        }
        List<PublicVisitTimeInfo> times = useCase.searchTimes(new TimesViewQuery(date));
        model.addAttribute("times", times);
    }

    @ModelAttribute
    public void addPatientTimeRequest(Model model) {
        model.addAttribute("info", new AppointmentInfo());
    }

}
