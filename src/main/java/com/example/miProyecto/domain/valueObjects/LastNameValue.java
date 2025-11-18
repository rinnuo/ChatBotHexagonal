package com.example.miProyecto.domain.valueObjects;

public class LastNameValue {

    private String value;

    public LastNameValue(String value) {
        if(value == null || value.isEmpty()){
            throw new IllegalArgumentException("El apellido no puede estar vacio");
        }
        if ( value.length() < 4) {
                throw new IllegalArgumentException("El nombre no puede ser menor a 4 caracteres");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
