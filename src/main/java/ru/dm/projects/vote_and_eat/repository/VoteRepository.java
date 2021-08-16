package ru.dm.projects.vote_and_eat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.dm.projects.vote_and_eat.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    @Query("SELECT v FROM Vote v WHERE v.date>=?1 AND v.date <=?2 ORDER BY v.date DESC")
    List<Vote> getBetween(LocalDate start, LocalDate end);

    @Query("SELECT v FROM Vote v JOIN v.user u WHERE u.email=?1")
    List<Vote> getByUser(String email);
    @Query("SELECT v FROM Vote v ORDER BY v.date DESC")
    List<Vote> getAll();

    @Query("SELECT v FROM Vote v WHERE v.date=?1")
    List<Vote> getForToday(LocalDate today);
}
