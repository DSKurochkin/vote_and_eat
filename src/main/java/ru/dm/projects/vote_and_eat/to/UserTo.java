package ru.dm.projects.vote_and_eat.to;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel(value = "User transfer object", description = "Object sent from the client's side to create or update User")
public class UserTo extends AbstractTo {

    @ApiModelProperty(notes = "user's name. may be between 2 to 50 characters and notblank")
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @ApiModelProperty(notes = "user's email. may be email up to 50 characters")
    @Email
    @NotBlank
    @Size(max = 50)
    private String email;

    @ApiModelProperty(notes = "user's password. may be between 5 to 30 characters and notblank")
    @NotBlank
    @Size(min = 5, max = 30)
    private String password;

    public UserTo() {

    }

    public UserTo(Long id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
