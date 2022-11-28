package ru.practicum.service;

import ru.practicum.dto.EndpointStatsClientDto;
import ru.practicum.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {
    EndpointStatsClientDto save(EndpointStatsClientDto endpointStatsClientDto);

    List<ViewStats> getViewStats(LocalDateTime startDate, LocalDateTime endDate, List<String> uriIds, Boolean unique);
}
