package ru.practicum.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.dto.StatsDto;
import ru.practicum.model.Stats;
import ru.practicum.model.ViewStats;

import java.util.List;

@Component
public class StatsMapper {

    public static Stats toStats(StatsDto endPointStatsDto) {
        return Stats.builder()
                .app(endPointStatsDto.getApp())
                .uri(endPointStatsDto.getUri())
                .ip(endPointStatsDto.getIp())
                .timestamp(endPointStatsDto.getTimestamp())
                .build();
    }

    public static ViewStats toViewStats(List<Stats> statHit) {
        if (statHit.isEmpty()) {
            return new ViewStats();
        }
        return new ViewStats(
                statHit.get(0).getApp(),
                statHit.get(0).getUri(),
                (long) statHit.size()
        );
    }
}
