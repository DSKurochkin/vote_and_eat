package ru.dm.projects.vote_and_eat.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dm.projects.vote_and_eat.model.User;

import java.util.List;

import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.ADMIN_URL;
import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.USER_URL;

@RestController
@RequestMapping(value = ADMIN_URL + USER_URL)
public class AdminController extends AbstractUserController {

    @Override
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> create(User user) {
        return super.create(user);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(User user, @PathVariable int id) {
        //chek user id
        repository.save(user);
    }

    @GetMapping
    public List<User> showAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public User show(@PathVariable int id) {
        return repository.getById(id);

    }


}
