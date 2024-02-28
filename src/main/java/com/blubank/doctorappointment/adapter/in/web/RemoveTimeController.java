package com.blubank.doctorappointment.adapter.in.web;

import com.blubank.doctorappointment.application.domain.model.VisitTimeId;
import com.blubank.doctorappointment.application.port.in.RemoveVisitTimeCommand;
import com.blubank.doctorappointment.application.port.in.RemoveVisitTimeUseCase;
import com.blubank.doctorappointment.common.WebAdapter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor/times")
@AllArgsConstructor
@WebAdapter
public class RemoveTimeController {

    private final RemoveVisitTimeUseCase removeVisitTimeUseCase;

    @PostMapping(path = "/{timeId}/remove")
    public ResponseEntity<String> remove(@PathVariable Long timeId) {
        removeVisitTimeUseCase.remove(new RemoveVisitTimeCommand(new VisitTimeId(timeId)));
        return ResponseEntity.ok().build();
    }
}
