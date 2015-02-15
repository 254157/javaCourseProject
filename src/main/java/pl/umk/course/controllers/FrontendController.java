package pl.umk.course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.umk.course.exceptions.EncodingException;
import pl.umk.course.forms.EncodeForm;
import pl.umk.course.repositories.EncodingEntityRepository;
import pl.umk.course.utils.Encoder;

import javax.validation.Valid;

@Controller
public class FrontendController {

    private final EncodingEntityRepository repository;
    private final Encoder encoder;

    @RequestMapping("/hello")
    public String indexAction(ModelMap model) {
        model.addAttribute("name", "Mirek");
        return "template";
    }

    @RequestMapping("/list")
    public String listAction(ModelMap model) {
        model.addAttribute("words", repository.findAll());
        return "list";
    }

    @RequestMapping(value = "/encodeForm", method = RequestMethod.GET)
    public String encodeFormAction(ModelMap model) {
        model.addAttribute("words", repository.findAll());
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        return "encode";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String encodeJsFormAction() {
        return "form";
    }

    @RequestMapping(value = "/encodeForm", method = RequestMethod.POST)
    public String encodeFormSubmitAction(ModelMap model, @Valid EncodeForm form, BindingResult result) throws EncodingException {
        if (result.hasErrors()) {
            model.addAttribute("error", "Formularz zawiera błędy");
            model.addAttribute("sourceText", form.getSourceText());
            return "encode";
        }
        else {
            String targetText = encoder.encrypt(form.getSourceText());
            model.addAttribute("sourceText", form.getSourceText());
            model.addAttribute("targetText", targetText);
            return "success";
        }
    }

    @Autowired
    public FrontendController(EncodingEntityRepository repository, Encoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }
}
