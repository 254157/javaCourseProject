package pl.umk.course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.umk.course.exceptions.EncodingException;
import pl.umk.course.utils.Encoder;

import javax.servlet.http.HttpServletResponse;

@RestController
public class EncodeController {

    private final Encoder encoder;


    @RequestMapping(value = "/encode/{text}", method = RequestMethod.GET, produces = "text/plain")
    public String encode(@PathVariable String text, HttpServletResponse response) {
        try {
            return encoder.encrypt(text);
        } catch (EncodingException e) {
            response.setStatus(400);
            return "Invalid text...";
        }
    }

    @Autowired
    public EncodeController(Encoder encoder) {
        this.encoder = encoder;
    }
}