package ru.dm.projects.vote_and_eat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dm.projects.vote_and_eat.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
