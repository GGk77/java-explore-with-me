package ru.practicum.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ApiError {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Builder.Default
    List<String> errors = new ArrayList<>();
    String message;
    String reason;
    ErrorStatus status;
    @Builder.Default
    LocalDateTime timestamp = LocalDateTime.now();
}
