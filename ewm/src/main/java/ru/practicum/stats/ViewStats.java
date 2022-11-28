package ru.practicum.stats;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewStats {
    String app;

    String uri;

    Integer hits;
}