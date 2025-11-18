package com.example.miProyecto.infrestructure.adapters.jpa;

import com.example.miProyecto.application.dto.UserDto;
import com.example.miProyecto.application.port.out.UserRepository;
import com.example.miProyecto.domain.models.User;
import com.example.miProyecto.infrestructure.adapters.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private UserJpaRepository userJpaRepository;
    private UserMapper userMapper;

    @Override
    public List<UserDto> getAll() {
        return userJpaRepository.findAll().stream().map( entity ->
            userMapper.ofEntityToDto(entity)
        ).toList();
    }

    @Override
    public UserDto getById(long id) {
        return userMapper.ofEntityToDto(
                userJpaRepository.findById(id).get()
        );
    }

    @Override
    public UserDto save(User user) {
        UserEntity entity = userJpaRepository.save(
                userMapper.ofModelToEntity(user)
        );
        return userMapper.ofEntityToDto(entity);
    }

    @Override
    public void delete(long id) {
        userJpaRepository.deleteById(id);
    }
}
