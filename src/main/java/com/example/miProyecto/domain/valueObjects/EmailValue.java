package com.example.miProyecto.domain.valueObjects;

import java.util.regex.Pattern;

public class EmailValue {

    private String value;
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    public EmailValue(String value) {
        if(value == null || value.isEmpty()){
            throw new IllegalArgumentException("El email no puede estar vacio");
        }
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Formato incorrecto, ejemplo: nombre@sitio.com");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
