package ru.practicum.compilation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.compilation.dto.CompilationDto;
import ru.practicum.compilation.service.CompilationService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/compilations")
@RequiredArgsConstructor
public class CompilationPublicController {

    @Autowired
    CompilationService compilationService;

    @GetMapping("/{compId}")
    @ResponseStatus(HttpStatus.OK)
    public CompilationDto getCompilationById(@PathVariable Integer compId) {
        log.info("GET /compilations/compId: {}", compId);
        return compilationService.getCompilationById(compId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompilationDto> getAllCompilationsWithParams(@RequestParam(required = false) Boolean pinned,
                                                             @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                             @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("GET all categories by params");
        return compilationService.getAllCompilationsWithParams(pinned, from, size);
    }
}
