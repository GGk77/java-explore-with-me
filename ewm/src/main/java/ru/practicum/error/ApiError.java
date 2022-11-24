package ru.practicum.error;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ApiError {
    @Builder.Default
    List<String> errors = new ArrayList<>();
    String message;
    String reason;
    ErrorStatus status;
    @Builder.Default
    LocalDateTime timestamp = LocalDateTime.now();


}
