package pl.umk.course.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    private String email;

    @NotNull
    private String password;




    public Long getId(){
        return Id;
    }

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



    public  UserEntity(){}

    public UserEntity(String email, String password){
        this.email = email;
        this.password = password;
    }

}