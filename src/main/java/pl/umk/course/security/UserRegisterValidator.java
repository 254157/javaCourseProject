package pl.umk.course.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.umk.course.forms.RegisterForm;
import pl.umk.course.repositories.UserEntityRepository;

/**
 * Created by emagdnim on 2015-02-11.
 */
@Component
public class UserRegisterValidator {

    @Autowired
    private UserEntityRepository repository;

    public boolean isValid(RegisterForm form){
        if(repository.findUserByEmail(form.getEmail()) == null){
            System.out.println("REPO: ---------------- NULL");
            if(form.getPassword().equals(form.getPasswordConfirm())){
                System.out.println("Password OK");
                return true;
            }
        }
        return false;
    }

}
