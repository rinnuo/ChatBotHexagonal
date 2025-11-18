package com.example.miProyecto.domain.valueObjects;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public final class TimeValue {

    private final String value;

    public TimeValue(String value) {
        if (value == null || value.trim().isEmpty()) {
            this.value = LocalDateTime.now().toString();
        } else {
            try {
                LocalDateTime parsed = LocalDateTime.parse(value);
                this.value = parsed.toString();
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException("Formato de tiempo inválido.");
            }
        }
    }

    public String getValue() {
        return value;
    }
}