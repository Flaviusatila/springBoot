package com.iftm.course.dto;

import com.iftm.course.entities.User;
import com.iftm.course.service.validations.UserUpdateValid;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@UserUpdateValid
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -5455982002017819112L;

    private Long id;

    @NotEmpty(message = "Nao pode ser vazio o nome")
    @Length(min = 5,max = 80, message = "O tamanho de ser entre 5 e 80 letras")
    private String name;

    @NotEmpty(message = "Nao pode ser vazio o email")
    @Email(message = "Problema na autenticação do email")
    private String email;

    @NotEmpty(message = "Nao pode ser vazio o phone")
    @Length(min = 8, max = 20, message = "O tamanho de ser entre 8 e 20 numeros")
    private String phone;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public UserDTO(User entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User toEntity(){
        return new User(id,name,email,phone,null);

    }

}
