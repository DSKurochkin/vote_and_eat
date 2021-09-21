package ru.dm.projects.vote_and_eat.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dm.projects.vote_and_eat.model.User;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = AdminController.ADMIN_USERS_URL)
public class AdminController extends AbstractUserController {
    static final String ADMIN_USERS_URL = "/admin/users";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        log.info("create {}", user);
        User created = userService.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_USERS_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody User user, @PathVariable long id) {
        log.info("update user with id = {}", id);
        userService.update(user, id);
    }

    @GetMapping
    public List<User> getAll() {
        log.info("get all users");
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) throws Exception {
        log.info("get user with id = {}", id);
        return userService.get(id);

    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws Exception {
        log.info("delete user with id = {}", id);
        userService.delete(id);
    }


}
