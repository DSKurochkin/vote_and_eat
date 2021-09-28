package ru.dm.projects.vote_and_eat.repository;

import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.dm.projects.vote_and_eat.model.Vote;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    @Query("SELECT v FROM Vote v WHERE v.date>=?1 AND v.date <=?2 ORDER BY v.date DESC")
    List<Vote> getBetween(LocalDate start, LocalDate end);

    @Query("SELECT v FROM Vote v ORDER BY v.date DESC")
    List<Vote> getAll();

    @Query("SELECT v FROM Vote v WHERE v.userId=?1 AND v.date=?2")
    Vote getIfTodayVoteExist(Long id, LocalDate date);

    @Query("SELECT v.restaurantId, v.restaurantName, COUNT(v.restaurantId) FROM Vote v GROUP BY v.restaurantId, v.restaurantName")
    List<List<String>> getReport();

}
