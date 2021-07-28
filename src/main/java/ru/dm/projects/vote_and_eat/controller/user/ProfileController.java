package ru.dm.projects.vote_and_eat.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import ru.dm.projects.vote_and_eat.model.User;

import static ru.dm.projects.vote_and_eat.controller.user.AbstractUserController.USER_URL;

@RestController
@RequestMapping(value = USER_URL)
public class ProfileController extends AbstractUserController {

//    @PutMapping("/{id}")
//    public void update(User user) {
//        Assert.notNull(user, "user must not be null");
//        //chek bean.id=id
//        repository.save(user);
//    }
//    //only own id
//    @PostMapping("/registry")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<User> registry(User user) {
//        Assert.notNull(user, "user must not be null");
//        User created = repository.save(user);
//        URI uriOfNewResourse= ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path(USER_URL).build().toUri();
//        return ResponseEntity.created(uriOfNewResourse).body(created);
//
//    }


    //only own id
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        //chek bean.id=id
        repository.save(user);

    }

    @Override
    @PostMapping("/registry")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> create(User user) {
        return super.create(user);
    }
}
