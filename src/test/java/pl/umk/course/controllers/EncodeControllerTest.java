package pl.umk.course.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletResponse;
import pl.umk.course.exceptions.EncodingException;
import pl.umk.course.utils.Encoder;

import javax.servlet.http.HttpServletResponse;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class EncodeControllerTest {
    @Mock
    Encoder encoder;
    @Mock
    HttpServletResponse response;
    EncodeController controller;

    @Test
    public void should_return_hello_spring_message_when_index_called() {
        String output = controller.index();

        assertThat(output).isEqualTo("Hello Spring!");
    }

    @Test
    public void should_return_encrypted_text_when_encode_called() throws EncodingException {
        when(encoder.encrypt("hello")).thenReturn("uryyb");

        String output = controller.encode("hello", response);

        assertThat(output).isEqualTo("uryyb");
    }

    @Test
    public void should_return_bad_request_when_encode_called_with_invalid_input() throws EncodingException {
        when(encoder.encrypt("Hello!!")).thenThrow(EncodingException.class);

        controller.encode("Hello!!", response);

        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Before
    public void setUp() {
        initMocks(this);
        controller = new EncodeController(encoder);
        response = new MockHttpServletResponse();
    }

}
