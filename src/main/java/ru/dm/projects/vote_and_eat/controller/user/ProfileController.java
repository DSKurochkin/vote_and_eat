package ru.dm.projects.vote_and_eat.controller.user;

import org.hibernate.engine.spi.ManagedEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dm.projects.vote_and_eat.model.User;
import ru.dm.projects.vote_and_eat.to.UserTo;
import ru.dm.projects.vote_and_eat.util.UserUtil;

import java.net.URI;



@RestController
@RequestMapping(value = ProfileController.PROFILE_URL)
public class ProfileController extends AbstractUserController {
    static final String PROFILE_URL = "/profile";
    //only own id
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UserTo userTo) {
        //chek bean.id=id
        User user=repository.findById(userTo.getId()).orElse(null);
        repository.save(UserUtil.updateFromTo(user,userTo));

    }


    @PostMapping(value = "/register" +
            "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> create(@RequestBody UserTo userTo) {
        User created = repository.save(UserUtil.createNewFromTo(userTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(PROFILE_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);

    }
}
