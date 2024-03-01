package com.blubank.doctorappointment.adapter.in.web;

import com.blubank.doctorappointment.application.domain.model.PublicVisitTimeInfo;
import com.blubank.doctorappointment.application.port.in.*;
import com.blubank.doctorappointment.common.WebAdapter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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

//        Object errors = redirectAttributes.getFlashAttributes().get("errors");
        ModelAndView view = new ModelAndView("times", model.asMap());
//        result.addError(new ObjectError("name", "adfasfas sfsaf as asf fs "));

        if (model.asMap().containsKey("errors")) {
//            model.addAttribute("fields.errors", model.asMap().get("errors"));
//            view.addObject("fields", ((BeanPropertyBindingResult) model.asMap().get("errors")).getAllErrors());
            List<ObjectError> errors = ((BeanPropertyBindingResult) model.asMap().get("errors")).getAllErrors();
            BindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "errors");
            errors.forEach(error -> bindingResult.addError(new ObjectError("errors", error.getDefaultMessage())));

            // Add BindingResult to the model with a key recognized by Thymeleaf
            model.addAttribute(BindingResult.MODEL_KEY_PREFIX + "errors", bindingResult);

        }
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
