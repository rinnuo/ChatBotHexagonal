package com.example.miProyecto.application.dto;

public class UserDto {

    public long id;
    public String name;
    public String lastName;

    public UserDto(long id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }
}
