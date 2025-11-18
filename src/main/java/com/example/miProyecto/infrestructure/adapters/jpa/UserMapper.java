package com.example.miProyecto.infrestructure.adapters.jpa;

import com.example.miProyecto.application.dto.UserDto;
import com.example.miProyecto.domain.models.User;
import com.example.miProyecto.infrestructure.adapters.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserEntity ofModelToEntity(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setPhone(user.getPhone());

        return userEntity;
    }

    public UserDto ofEntityToDto(UserEntity entity){
        return new UserDto(
                entity.getId(),
                entity.getName(),
                entity.getLastName()
        );
    }
}
