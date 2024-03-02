package com.blubank.doctorappointment.adapter.in.web;

import com.blubank.doctorappointment.application.domain.model.VisitTime;
import com.blubank.doctorappointment.application.port.in.DoctorTimesQuery;
import com.blubank.doctorappointment.application.port.in.OpenTimeServiceUseCase;
import com.blubank.doctorappointment.application.port.in.ViewDoctorTimesUseCase;
import com.blubank.doctorappointment.common.DateUtils;
import com.blubank.doctorappointment.common.WebAdapter;
import com.blubank.doctorappointment.common.dto.DateTimeDto;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@WebAdapter
@RequestMapping("/clinic")
@AllArgsConstructor
public class ShowTimesToDoctorWebAdapter {

    private final OpenTimeServiceUseCase openTimeServiceUseCase;
    private final ViewDoctorTimesUseCase viewDoctorTimesUseCase;

    @GetMapping()
    public String homePage(Model model) {

//        handleErrors(model);
        return "doctor-times";
    }

    static void handleErrors(Model model) {
        if (model.asMap().containsKey("errors")) {
//            model.addAttribute("fields.errors", model.asMap().get("errors"));
//            view.addObject("fields", ((BeanPropertyBindingResult) model.asMap().get("errors")).getAllErrors());



         /*   List<ObjectError> errors;
            if ((model.asMap().get("errors")) instanceof BeanPropertyBindingResult) {
                errors = ((BeanPropertyBindingResult) model.asMap().get("errors")).getAllErrors();
            } else {
                errors = List.of(new ObjectError("errors", model.asMap().get("errors").toString()));
            }
            BindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "errors");
            errors.forEach(error -> bindingResult.addError(new ObjectError("errors", error.getDefaultMessage())));

            // Add BindingResult to the model with a key recognized by Thymeleaf
            model.addAttribute(BindingResult.MODEL_KEY_PREFIX + "errors", bindingResult);
            */

        }
    }

    @GetMapping("/times")
    public String getTimes(@RequestParam(value = "date") String dateStr, Model model) {
        DateTimeDto date = DateUtils.parseDate(dateStr, "0:0");
        var times = viewDoctorTimesUseCase.viewTimes(new DoctorTimesQuery(date));
        model.addAttribute("times", times);
        return "redirect:/clinic";
    }

    private DateTimeDto dayOf(LocalDateTime date) {
        return DateTimeDto.of(date);
    }

    @ModelAttribute
    public void addTimes(@RequestParam(value = "date", required = false) String dateStr, Model model) {
        DateTimeDto date;
        if (StringUtils.isNotBlank(dateStr)) {
            date = DateUtils.parseDate(dateStr, "0:0");
        } else {
            date = dayOf(LocalDateTime.now());
        }
        List<VisitTime> times = viewDoctorTimesUseCase.viewTimes(new DoctorTimesQuery(date));
        model.addAttribute("times", times);
    }

    @ModelAttribute
    public void addPatientTimeRequest(Model model) {
        model.addAttribute("info", new DateTimeDto());
    }
}
