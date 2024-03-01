package com.blubank.doctorappointment.adapter.in.web;

import com.blubank.doctorappointment.application.domain.model.VisitTimeId;
import com.blubank.doctorappointment.application.port.in.*;
import com.blubank.doctorappointment.common.WebAdapter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@WebAdapter
@AllArgsConstructor
public class TakeAppointmentWebAdapter {

    private final TakeAppointmentUseCase useCase;

    @PostMapping("/take")
    public String homePage(@ModelAttribute("info") @Valid AppointmentInfo info, Errors errors, Model model,
                           RedirectAttributes redirectAttributes /*,BindingResult result, Model model*/) {
        if (errors.hasErrors()) {
//            model.addAttribute("errors", errors);
            redirectAttributes.addFlashAttribute("errors", errors);
//           return new ModelAndView("times", HttpStatus.BAD_REQUEST);
            return "redirect:/times";
        }
        LocalDateTime date = LocalDateTime.of(2024, 2, 28, 0, 0);
        TakeAppointmentCommand command = createCommand(info);
        useCase.take(command);
        addMessage(redirectAttributes, "Appointment booked successfully");
//        return new ModelAndView("times", HttpStatus.OK);

        return "redirect:/times";
    }

    private void addMessage(RedirectAttributes model, String message) {
        model.addFlashAttribute("message", message);
    }

    private static TakeAppointmentCommand createCommand(AppointmentInfo info) {
        return new TakeAppointmentCommand(new PatientInfo(info.getName(), info.getPhoneNumber()), new VisitTimeId(info.getTimeId()));
    }
}
