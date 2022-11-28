package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import ru.practicum.model.EndpointStatsClient;
import ru.practicum.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public interface StatsRepository extends JpaRepository<EndpointStatsClient, Integer> {

    @Query("SELECT new ru.practicum.model.ViewStats(e.app, e.uri, COUNT (e.ip)) " +
            "from EndpointStatsClient e WHERE e.timestamp> ?1 AND e.timestamp< ?2 GROUP BY e.app, e.uri")
    List<ViewStats> getAll(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query("SELECT new ru.practicum.model.ViewStats(e.app, e.uri, COUNT (DISTINCT e.ip)) from " +
            "EndpointStatsClient e WHERE e.timestamp> ?1 AND e.timestamp< ?2 GROUP BY e.app, e.uri")
    List<ViewStats> getAllUnique(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique);
}
