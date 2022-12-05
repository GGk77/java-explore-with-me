package ru.practicum.compilation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Jacksonized
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewCompilationDto {

    @NotBlank
    String title;

    Boolean pinned;

    List<Integer> events;

}
