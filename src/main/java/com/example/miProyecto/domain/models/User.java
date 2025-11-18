package com.example.miProyecto.domain.models;

import com.example.miProyecto.domain.valueObjects.NameValue;
import com.example.miProyecto.domain.valueObjects.LastNameValue;
import com.example.miProyecto.domain.valueObjects.EmailValue;
import com.example.miProyecto.domain.valueObjects.PasswordValue;

public class User {

    private long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phone;

    public User() {
    }

    public User(long id, String name, String lastName, String email, String password, String phone) {
        this.id = id;
        this.name = new NameValue(name).getValue();
        this.lastName = new LastNameValue(lastName).getValue();
        this.email = new EmailValue(email).getValue();
        this.password = new PasswordValue(password).getValue();
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
