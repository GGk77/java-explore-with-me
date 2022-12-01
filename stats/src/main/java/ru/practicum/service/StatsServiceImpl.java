package ru.practicum.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.dto.StatsDto;
import ru.practicum.mapper.StatsMapper;
import ru.practicum.model.Stats;
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
    public StatsDto save(StatsDto statsDto) {
        Stats stats = StatsMapper.toStats(statsDto);
        stats.setTimestamp(LocalDateTime.now());
        return StatsMapper.toStatsDto(statsRepository.save(stats));
    }

    @Override
    public List<ViewStats> getViewStats(LocalDateTime startDate, LocalDateTime endDate, List<String> uris, Boolean unique) {
        if (unique) {
            return statsRepository.getAllUnique(startDate, endDate, uris, unique);
        } else {
            return statsRepository.getAll(startDate, endDate, uris);
        }
    }
}
