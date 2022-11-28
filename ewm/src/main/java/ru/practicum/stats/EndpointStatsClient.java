package ru.practicum.stats;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EndpointStatsClient {

    Integer id;

    @NotBlank
    String uri;
    @NotBlank
    String app;
    @NotBlank
    String ip;

    @NotNull
    LocalDateTime timestamp;

    public EndpointStatsClient(String app, String uri, String ip) {
        this.app = app;
        this.uri = uri;
        this.ip = ip;
    }
}