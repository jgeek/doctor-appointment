package com.blubank.doctorappointment.adapter.in.web;

import com.blubank.doctorappointment.application.domain.model.VisitTimeId;
import com.blubank.doctorappointment.application.port.in.*;
import com.blubank.doctorappointment.common.WebAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@WebAdapter
@RequestMapping("/clinic/times")
@AllArgsConstructor
public class RemoveTimeWebAdapter {

    private final RemoveVisitTimeUseCase useCase;

    @PostMapping("/remove")
    public String openTimes(@RequestParam(value = "remove") Long timeId) {
        VisitTimeId visitTimeId = new VisitTimeId(timeId);
        useCase.remove(new RemoveVisitTimeCommand(visitTimeId));
        return "redirect:/clinic";
    }
}
