package ru.dm.projects.vote_and_eat.controller.user;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dm.projects.vote_and_eat.model.User;
import ru.dm.projects.vote_and_eat.security.SecurityUtil;
import ru.dm.projects.vote_and_eat.to.UserTo;
import ru.dm.projects.vote_and_eat.util.UserUtil;
import ru.dm.projects.vote_and_eat.util.exception.IllegalRequestDataException;

import javax.validation.Valid;
import java.net.URI;

import static ru.dm.projects.vote_and_eat.security.SecurityUtil.authUserId;
import static ru.dm.projects.vote_and_eat.util.UserUtil.asTo;

@RestController
@RequestMapping(value = ProfileController.PROFILE_URL)
public class ProfileController extends AbstractUserController {
    static final String PROFILE_URL = "/profile";

    @ApiOperation(value = "registration and creation of a new user", response = User.class)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserTo userTo) throws Exception {
        Long toId = userTo.getId();
        Long authId = authUserId();
        log.info("update user profile with id = {} ", authId);
        if (!toId.equals(authId)) {
            throw new IllegalRequestDataException("This user can't update user with id=" + toId);
        }
        User user = UserUtil.createFromTo(SecurityUtil.get().getUserTo());
        userService.update(UserUtil.updateFromTo(user, userTo), authUserId());
    }

    @ApiOperation(value = "get user own profile", response = UserTo.class)
    @GetMapping
    public UserTo get() throws Exception {
        Long id = authUserId();
        log.info("get user with id = {}", id);
        return asTo(userService.get(id));
    }

    @ApiOperation(value = "update own profile by user")
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> create(@Valid @RequestBody UserTo userTo) {
        log.info("create user = {} ", userTo);
        User created = userService.create(UserUtil.createNewFromTo(userTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(PROFILE_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
