package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import ru.practicum.model.Stats;
import ru.practicum.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public interface StatsRepository extends JpaRepository<Stats, Integer> {

    @Query("SELECT new ru.practicum.model.ViewStats(s.app, s.uri, COUNT (s.ip)) " +
            "FROM Stats s " +
            "WHERE s.timestamp> ?1 AND s.timestamp< ?2 " +
            "AND s.uri IN ?3 " +
            "GROUP BY s.app, s.uri")
    List<ViewStats> getAll(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query("SELECT new ru.practicum.model.ViewStats(s.app, s.uri, COUNT (DISTINCT s.ip)) " +
            "FROM Stats s " +
            "WHERE s.timestamp> ?1 AND s.timestamp< ?2 " +
            "AND s.uri IN ?3 " +
            "GROUP BY s.app, s.uri")
    List<ViewStats> getAllUnique(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique);
}
