package ru.practicum.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.dto.EndpointStatsClientDto;
import ru.practicum.mapper.EndpointStatsMapper;
import ru.practicum.model.EndpointStatsClient;
import ru.practicum.model.ViewStats;
import ru.practicum.repository.StatsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class StatsServiceImpl implements StatsService {

    @Autowired
    StatsRepository statsRepository;
    @Override
    public EndpointStatsClientDto save(EndpointStatsClientDto endpointStatsClientDto) {
        EndpointStatsClient endpointStatsClient = EndpointStatsMapper.toEndpointStatsClient(endpointStatsClientDto);
        return EndpointStatsMapper.toEndpointStatsClientDto(statsRepository.save(endpointStatsClient));
    }

    @Override
    public List<ViewStats> getViewStats(LocalDateTime startDate, LocalDateTime endDate, List<String> uriIds, Boolean unique) {
        if(!unique) {
            return statsRepository.getAll(startDate, endDate, uriIds);
        } else return statsRepository.getAllUnique(startDate, endDate, uriIds, unique);
    }
}
