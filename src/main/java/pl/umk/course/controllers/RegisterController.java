package pl.umk.course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.umk.course.entities.UserEntity;
import pl.umk.course.forms.RegisterForm;
import pl.umk.course.repositories.UserEntityRepository;
import pl.umk.course.security.UserRegisterValidator;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final UserEntityRepository repository;
    private final UserRegisterValidator userValidator;

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String registerFormAction(){
        return "register";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String registerFormSubmit(ModelMap model, @Valid @ModelAttribute("RegisterForm") RegisterForm form, BindingResult result) {

        if (result.hasErrors() || !userValidator.isValid(form)) {

            if(result.hasErrors())
                System.out.println(form.toString());
            if(!userValidator.isValid(form))
                System.out.println(form.toString());

            model.addAttribute("error", "Formularz zawiera błędy");
            model.addAttribute("email", form.getEmail());
            model.addAttribute("password", form.getEmail());
            return "register";
        }

        try{
            System.out.println(form.toString());
            repository.save(new UserEntity(form.getEmail(), form.getPassword()));
            System.out.println("User registered");
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return "redirect:/login";
    }

    @RequestMapping(value="/terms")
    public String terms(){
        return "terms";
    }

    @Autowired
    public RegisterController(UserEntityRepository repository, UserRegisterValidator userValidator){
        this.repository = repository;
        this.userValidator = userValidator;
    }
}
