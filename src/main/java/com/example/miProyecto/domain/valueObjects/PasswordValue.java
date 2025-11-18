package com.example.miProyecto.domain.valueObjects;

public class PasswordValue {

    private String value;

    public PasswordValue(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : value.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else hasSpecial = true;
        }

        if (!hasUpper)
            throw new IllegalArgumentException("La contraseña debe contener al menos una letra mayúscula");
        if (!hasLower)
            throw new IllegalArgumentException("La contraseña debe contener al menos una letra minúscula");
        if (!hasDigit)
            throw new IllegalArgumentException("La contraseña debe contener al menos un número");
        if (!hasSpecial)
            throw new IllegalArgumentException("La contraseña debe contener al menos un carácter especial");

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
