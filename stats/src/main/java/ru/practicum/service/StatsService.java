package ru.practicum.service;

import ru.practicum.dto.StatsDto;
import ru.practicum.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {
    void save(StatsDto statsDto);

    List<ViewStats> getViewStats(LocalDateTime startDate, LocalDateTime endDate, List<String> uris, Boolean unique);
}
