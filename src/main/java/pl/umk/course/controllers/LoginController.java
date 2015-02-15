package pl.umk.course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.umk.course.repositories.UserEntityRepository;
import pl.umk.course.security.Role;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by emagdnim on 2015-02-10.
 */
@Controller
public class LoginController {

    @Autowired
    private UserEntityRepository repository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.USER.toString()))
            return new ModelAndView("redirect:/");

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username or password!");
        }

        if (logout != null) {
            model.addObject("logout", "You've been logged out successfully.");
        }
        model.addObject("username", "username");
        model.setViewName("login");

        model.addObject("accounts", repository.findAll());
        return model;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginPOST(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        model.addObject("accounts", repository.findAll());
        return model;
    }
}