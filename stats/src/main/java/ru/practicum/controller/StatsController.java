package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.StatsDto;
import ru.practicum.model.ViewStats;
import ru.practicum.service.StatsService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class StatsController {

    @Autowired
    StatsService statsService;

    @PostMapping("/hit")
    public void save(@Valid @RequestBody StatsDto statsDto) {
        log.info(" uri in controller: " + statsDto.getUri());
        statsService.save(statsDto);
    }

    @GetMapping("/stats")
    public List<ViewStats> getViewStats(
            @RequestParam(name = "start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
            @RequestParam(name = "end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
            @RequestParam(name = "uris") List<String> uris,
            @RequestParam(name = "unique") Boolean unique) {
        log.info("get views with params");
        return statsService.getViewStats(start, end, uris, unique);
    }
}