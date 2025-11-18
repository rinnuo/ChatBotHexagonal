package com.example.miProyecto.application.services;

import com.example.miProyecto.application.dto.UserDto;
import com.example.miProyecto.application.port.out.UserRepository;
import com.example.miProyecto.domain.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {
    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public List<UserDto> getAll(){
        return userRepository.getAll();
    }

    public UserDto getById(long id){
        return userRepository.getById(id);
    }

    public UserDto save(User user){
        return userRepository.save(user);
    }

    public void delete(long id){
        userRepository.delete(id);
    }


}
