package ru.practicum.compilation.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Jacksonized
public class NewCompilationDto {

    List<Integer> eventsId;

    Boolean pinned;

    @NotBlank
    String title;
}
