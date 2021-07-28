package ru.dm.projects.vote_and_eat.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dm.projects.vote_and_eat.model.User;
import ru.dm.projects.vote_and_eat.repository.UserRepository;

import java.net.URI;

public class AbstractUserController {
    public static final String ADMIN_URL = "/admin";
    static final String USER_URL = "/users";
    @Autowired
    UserRepository repository;

    public ResponseEntity<User> create(User user) {
        Assert.notNull(user, "user must not be null");
        User created = repository.save(user);
        URI uriOfNewResourse = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(USER_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResourse).body(created);

    }

}
