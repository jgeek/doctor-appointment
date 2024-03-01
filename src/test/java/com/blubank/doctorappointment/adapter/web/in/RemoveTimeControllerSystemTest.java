package com.blubank.doctorappointment.adapter.web.in;

import com.blubank.doctorappointment.application.domain.model.VisitTimeId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RemoveTimeControllerSystemTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Sql("file:src/test/resources/default-times.sql")
    void time_remove_successfully() {

        ResponseEntity response = whenRemove(notTakenTimeId());
        then(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    @Sql("file:src/test/resources/default-times.sql")
    void removing_taken_time_result_406_error() {

        ResponseEntity response = whenRemove(takenTimeId());
        then(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_ACCEPTABLE);
    }

    private ResponseEntity whenRemove(VisitTimeId visitTimeId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<Void> request = new HttpEntity<>(null, headers);
        return restTemplate.postForEntity(
                "/doctor/times/{timeId}/remove",
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
