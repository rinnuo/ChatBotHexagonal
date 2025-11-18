package com.example.miProyecto.application.port.out;

import com.example.miProyecto.application.dto.UserDto;
import com.example.miProyecto.domain.models.User;

import java.util.List;

public interface UserRepository {

    public List<UserDto> getAll();
    public UserDto getById(long id);
    public UserDto save(User user);
    public void delete(long id);

}
