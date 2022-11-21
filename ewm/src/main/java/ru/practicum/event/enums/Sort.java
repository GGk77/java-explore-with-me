package ru.practicum.event.enums;

import java.util.Optional;

public enum Sort {

    EVENT_DATE,
    VIEWS;

    public static Optional<Sort> from(String sort) {
        for (Sort stringSort : values()) {
            if (stringSort.name().equalsIgnoreCase(sort)) {
                return Optional.of(stringSort);
            }
        }
        return Optional.empty();
    }
}