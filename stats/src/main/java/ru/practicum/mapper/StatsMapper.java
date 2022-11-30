package ru.practicum.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.dto.StatsDto;
import ru.practicum.model.Stats;

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

    public static StatsDto toStatsDto(Stats endPointStats) {
        return StatsDto.builder()
                .id(endPointStats.getId())
                .app(endPointStats.getApp())
                .uri(endPointStats.getUri())
                .ip(endPointStats.getIp())
                .timestamp(endPointStats.getTimestamp())
                .build();
    }
}
