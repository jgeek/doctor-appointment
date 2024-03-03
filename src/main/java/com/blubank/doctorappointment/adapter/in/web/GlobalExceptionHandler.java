package com.blubank.doctorappointment.adapter.in.web;

import com.blubank.doctorappointment.application.domain.service.InvalidTimeException;
import com.blubank.doctorappointment.common.exception.TimesForDayAlreadyOpened;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TimesForDayAlreadyOpened.class)
    public String handleTimesForDayAlreadyOpened(Exception e,  RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        return "redirect:/clinic";
    }

    @ExceptionHandler({InvalidTimeException.class,})
    public String handleInvalidTimeException(Exception e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        return "redirect:/clinic";
//        return new ModelAndView("/clinic", HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Exception.class)
//    public String unHandledExceptions(Exception e,  RedirectAttributes redirectAttributes, Model model) {
//        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
//        return "redirect:/clinic";
//    }
}