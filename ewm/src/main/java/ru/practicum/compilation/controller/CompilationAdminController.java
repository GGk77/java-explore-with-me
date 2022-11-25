package ru.practicum.compilation.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.compilation.dto.CompilationDto;
import ru.practicum.compilation.dto.NewCompilationDto;
import ru.practicum.compilation.service.CompilationService;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping(value = "/admin/compilations")
@RequiredArgsConstructor
public class CompilationAdminController {

    @Autowired
    CompilationService compilationService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CompilationDto create(@Valid @RequestBody NewCompilationDto newCompilationDto) {
            log.info("POST /admin/compilations: {}", newCompilationDto);
            return compilationService.create(newCompilationDto);
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable @NonNull Integer compId) {
        log.info("DELETE /admin/compilations/compId: {}", compId);
        compilationService.delete(compId);
    }

    @PatchMapping("/{compId}/pin")
    @ResponseStatus(HttpStatus.OK)
    public void createPinInCompilation(@PathVariable Integer compId) {
        log.info("PATCH /{compId}/pin: {}", compId);
        compilationService.createPinInCompilation(compId);
    }

    @DeleteMapping("/{compId}/pin")
    @ResponseStatus(HttpStatus.OK)
    public void deletePinInCompilation(@PathVariable Integer compId) {
        log.info("DELETE /compId/pin: {}", compId);
        compilationService.deletePinInCompilation(compId);
    }

    @PatchMapping("/{compId}/events/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public void createEventInCompilation(@PathVariable Integer compId, @PathVariable Integer eventId) {
        log.info("PATCH /compId/events/eventId: {}", compId);
        log.info("PATCH /compId/events/eventId: {}", eventId);
        compilationService.createEventInCompilation(compId, eventId);
    }

    @DeleteMapping("/{compId}/events/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEventInCompilation(@PathVariable Integer compId, @PathVariable Integer eventId) {
        log.info("DELETE /compId/events/eventId: {}", compId);
        log.info("DELETE /compId/events/eventId: {}", eventId);
        compilationService.deleteEventInCompilation(compId, eventId);
    }
}
