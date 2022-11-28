package ru.practicum.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.dto.EndpointStatsClientDto;
import ru.practicum.model.EndpointStatsClient;

@Component
public class EndpointStatsMapper {

    public static EndpointStatsClient toEndpointStatsClient(EndpointStatsClientDto endPointStatsClientDto) {
        return EndpointStatsClient.builder()
                .app(endPointStatsClientDto.getApp())
                .uri(endPointStatsClientDto.getUri())
                .ip(endPointStatsClientDto.getIp())
                .timestamp(endPointStatsClientDto.getTimestamp())
                .build();
    }

    public static EndpointStatsClientDto toEndpointStatsClientDto(EndpointStatsClient endPointStatsClient) {
        return EndpointStatsClientDto.builder()
                .id(endPointStatsClient.getId())
                .app(endPointStatsClient.getApp())
                .uri(endPointStatsClient.getUri())
                .ip(endPointStatsClient.getIp())
                .timestamp(endPointStatsClient.getTimestamp())
                .build();
    }
}
