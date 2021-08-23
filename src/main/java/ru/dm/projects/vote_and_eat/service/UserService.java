package ru.dm.projects.vote_and_eat.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.dm.projects.vote_and_eat.model.User;
import ru.dm.projects.vote_and_eat.repository.UserRepository;
import ru.dm.projects.vote_and_eat.security.AuthorizedUser;

import java.util.List;

import static ru.dm.projects.vote_and_eat.util.ValidationUtil.notFoundMessage;

@Service("userService")
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository repository;

    public User get(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessage(id)));
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User createOrUpdate(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    public void delete(Long id) throws Exception {
        repository.delete(get(id));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getUserByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User with email: " + email + " doesn't exist");
        }
        return new AuthorizedUser(user);
    }
}
