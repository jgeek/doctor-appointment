package com.blubank.doctorappointment.adapter.web.in;

import com.blubank.doctorappointment.application.domain.model.VisitTimeId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(value = "classpath:default-times.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class RemoveTimeControllerSystemTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    void time_remove_successfully() {

        ResponseEntity response = whenRemove(notTakenTimeId());
        then(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    @Transactional
    void removing_taken_time_result_406_error() {

        ResponseEntity response = whenRemove(takenTimeId());
        then(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_ACCEPTABLE);
    }

    private ResponseEntity whenRemove(VisitTimeId visitTimeId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<Void> request = new HttpEntity<>(null, headers);
        return restTemplate.exchange(
                "/doctor/times/{timeId}/remove",
                HttpMethod.POST,
                request,
                Object.class,
                visitTimeId.id());
    }

    private VisitTimeId notTakenTimeId() {
        return new VisitTimeId(1L);
    }

    private VisitTimeId takenTimeId() {
        return new VisitTimeId(2L);
    }
}
