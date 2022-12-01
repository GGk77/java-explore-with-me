package ru.practicum.stats;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class StatsMapper {
    public static EwmStatsDto toEndpointHitDto(String endpoint, HttpServletRequest request) {
        return EwmStatsDto.builder()
                .app(endpoint)
                .uri(request.getRequestURI())
                .ip(request.getRemoteAddr())
                .timestamp(LocalDateTime.now())
                .build();
    }
}