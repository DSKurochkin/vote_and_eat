package ru.dm.projects.vote_and_eat.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dm.projects.vote_and_eat.model.User;
import ru.dm.projects.vote_and_eat.util.UserUtil;

import java.net.URI;
import java.util.List;



@RestController
@RequestMapping(value = AdminController.ADMIN_USERS_URL)
public class AdminController extends AbstractUserController {
    static final String ADMIN_USERS_URL = "/admin/users";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> create(@RequestBody User user) {
        User created = repository.save(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_USERS_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(User user, @PathVariable int id) {
        //chek user id
        repository.save(user);
    }

    @GetMapping
    public List<User> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        return repository.getById(id);

    }


}
