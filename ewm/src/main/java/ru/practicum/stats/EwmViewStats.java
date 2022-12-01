package ru.practicum.stats;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EwmViewStats {
    String app;

    String uri;

    Integer hits;
}