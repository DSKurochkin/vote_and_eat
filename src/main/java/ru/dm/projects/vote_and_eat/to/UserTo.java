package ru.dm.projects.vote_and_eat.to;

public class UserTo extends AbstractTo {

    private String email;

    private String password;

    public UserTo() {

    }

    public UserTo(Long id, String name, String email, String password) {
        super(id, name);
        this.email = email;
        this.password = password;
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
