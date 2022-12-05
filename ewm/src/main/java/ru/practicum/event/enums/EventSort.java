package ru.practicum.event.enums;

import java.util.Optional;

public enum EventSort {

    EVENT_DATE,
    VIEWS;

    public static Optional<EventSort> from(String sort) {
        for (EventSort stringEventSort : values()) {
            if (stringEventSort.name().equalsIgnoreCase(sort)) {
                return Optional.of(stringEventSort);
            }
        }
        return Optional.empty();
    }
}