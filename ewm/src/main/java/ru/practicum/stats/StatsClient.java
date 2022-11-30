package ru.practicum.stats;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatsClient  {

    private final WebClient webClient;

    @Value("${app-name}")
    private String appName;

    public void save(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        String uri = request.getRequestURI();
        EwmStatsDto ewmStatsDto = new EwmStatsDto(appName, uri, ip);
        log.info(" uri in request" + uri);
        webClient.post()
                .uri("/hit")
                .body(BodyInserters.fromValue(ewmStatsDto))
                .retrieve()
                .bodyToMono(EwmStatsDto.class)
                .block();
    }

}