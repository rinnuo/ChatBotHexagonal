package com.example.miProyecto.domain.valueObjects;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class HttpMethodValue {

    private static final Set<String> ALLOWED = new HashSet<>(
            Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS", "HEAD")
    );

    private final String value;

    public HttpMethodValue(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Http method no puede estar vacío");
        }
        String normalized = value.trim().toUpperCase();
        if (!ALLOWED.contains(normalized)) {
            throw new IllegalArgumentException("Http method no permitido: " + value);
        }
        this.value = normalized;
    }

    public String getValue() {
        return value;
    }
}