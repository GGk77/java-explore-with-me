package ru.practicum.event.enums;

import java.util.Optional;

public enum EventState {

    PENDING,
    PUBLISHED,
    REJECTED,
    CANCELED;

    public static Optional<EventState> from(String eventState) {
        for (EventState state : values()) {
            if (state.name().equalsIgnoreCase(eventState)) {
                return Optional.of(state);
            }
        }
        return Optional.empty();
    }
}