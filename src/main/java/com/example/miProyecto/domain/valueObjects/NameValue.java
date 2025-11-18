package com.example.miProyecto.domain.valueObjects;

public class NameValue {

    private String value;

    public NameValue(String value) {
        if(value == null || value.isEmpty()){
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        if ( value.length() > 30) {
            throw new IllegalArgumentException("El nombre no puede ser mayor a 30 caracteres");
        }
        if ( value.length() < 2) {
                throw new IllegalArgumentException("El nombre no puede ser menor a 2 caracteres");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
