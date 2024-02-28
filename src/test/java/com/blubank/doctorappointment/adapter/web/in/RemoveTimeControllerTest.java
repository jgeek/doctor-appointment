package com.blubank.doctorappointment.adapter.web.in;

import com.blubank.doctorappointment.adapter.in.web.RemoveTimeController;
import com.blubank.doctorappointment.application.domain.model.VisitTimeId;
import com.blubank.doctorappointment.application.port.in.RemoveVisitTimeCommand;
import com.blubank.doctorappointment.application.port.in.RemoveVisitTimeUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RemoveTimeController.class)
public class RemoveTimeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RemoveVisitTimeUseCase removeTimeUseCase;

    @Test
    void testRemoveTime() throws Exception {

        mockMvc.perform(post("/doctor/times/{timeId}/remove", 1)
                        .header("Content-Type", "application/json"))
                .andExpect(status().isOk());

        then(removeTimeUseCase).should()
                .remove(eq(new RemoveVisitTimeCommand(new VisitTimeId(1L))));
    }

}
