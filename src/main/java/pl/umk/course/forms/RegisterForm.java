package pl.umk.course.forms;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by emagdnim on 2015-02-11.
 */
public class RegisterForm {

    @NotEmpty
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email = "";

    @NotEmpty
    @Size(min=4)
    private String password = "";

    @NotEmpty
    @Size(min=4)
    private String passwordConfirm = "";

    @AssertTrue
    private boolean acceptTerms = false;




    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }

    public void setPasswordConfirm(String password_confirm){
        this.passwordConfirm = password_confirm;
    }
    public String getPasswordConfirm(){
        return passwordConfirm;
    }

    public void setAcceptTerms(boolean acceptTerms)
    {
        this.acceptTerms = acceptTerms;
    }

    public boolean getAcceptTerms()
    {
        return this.acceptTerms;
    }

    public RegisterForm(){}

    public RegisterForm(String email, String password, String passwordConfirm){
        this.email=email;
        this.password=password;
        this.passwordConfirm = passwordConfirm;
    }

    public String toString(){
        return "RegisterForm: email:" + this.email + " pass: " + this.password + "confPass: " + this.passwordConfirm;
    }
}
