package ru.practicum.request.enums;

import java.util.Optional;

public enum Status {

    PENDING,
    CONFIRMED,
    REJECTED,
    CANCELED;

    public static Optional<Status> from(String text) {
        for (Status status : values()) {
            if (status.name().equalsIgnoreCase(text)) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }
}
