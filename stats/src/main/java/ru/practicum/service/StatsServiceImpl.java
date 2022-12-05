package ru.practicum.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.dto.StatsDto;
import ru.practicum.mapper.StatsMapper;
import ru.practicum.model.ViewStats;
import ru.practicum.repository.StatsRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class StatsServiceImpl implements StatsService {

    @Autowired
    StatsRepository statsRepository;

    @Override
    public void save(StatsDto statsDto) {
        statsRepository.save(StatsMapper.toStats(statsDto));
    }

    @Override
    public List<ViewStats> getViewStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        List<ViewStats> viewStats = new ArrayList<>();
        for (String uri : uris) {
            if (!unique && start.equals(end)) {
                viewStats.add(StatsMapper.toViewStats(
                        statsRepository.findAllByUri(uri)));
            } else if (unique && start.equals(end)) {
                viewStats.add(StatsMapper.toViewStats(
                        statsRepository.findDistinctByUriAndIpAndApp(uri)));
            } else if (!unique && !start.equals(end)) {
                viewStats.add(StatsMapper.toViewStats(
                        statsRepository.findAllByUriAndTimestampBetween(uri, start, end)));
            } else if (unique && !start.equals(end)) {
                viewStats.add(StatsMapper.toViewStats(
                        statsRepository.findDistinctByUriAndTimestampBetween(uri, start, end)));
            }
        }
        return viewStats;
    }
}
