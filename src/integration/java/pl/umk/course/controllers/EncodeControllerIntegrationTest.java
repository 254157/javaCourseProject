package pl.umk.course.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import pl.umk.course.Application;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class EncodeControllerIntegrationTest {

    private static final String INDEX_URL = "http://localhost:8080/";
    private static final String ENCODE_URL = "http://localhost:8080/encode/";

    RestTemplate template = new TestRestTemplate();

    @Test
    public void should_return_index_page() {
        ResponseEntity<String> responseEntity = template.getForEntity(INDEX_URL, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("Hello Spring!");
    }

    @Test
    public void should_return_encoded_page_when_valid_input() {
        ResponseEntity<String> responseEntity = template.getForEntity(ENCODE_URL + "abc", String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("nop");
    }

    @Test
    public void should_return_400_status_when_invalid_input() {
        ResponseEntity<String> responseEntity = template.getForEntity(ENCODE_URL + "INVALID!", String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

}
