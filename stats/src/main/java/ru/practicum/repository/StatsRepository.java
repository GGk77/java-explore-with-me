package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import ru.practicum.model.Stats;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public interface StatsRepository extends JpaRepository<Stats, Integer> {

    List<Stats> findAllByUri(String uri);

    @Query(value = "SELECT DISTINCT s.app, s.uri, s.ip FROM Stats as s WHERE s.uri = ?1")
    List<Stats> findDistinctByUriAndIpAndApp(String uri);

    List<Stats> findAllByUriAndTimestampBetween(String uri, LocalDateTime start, LocalDateTime end);

    @Query(value = "SELECT DISTINCT s.app, s.uri, s.ip FROM Stats as s WHERE s.uri = ?1 AND s.timestamp BETWEEN ?2 AND ?3")
    List<Stats> findDistinctByUriAndTimestampBetween(String uri, LocalDateTime start, LocalDateTime end);
}
