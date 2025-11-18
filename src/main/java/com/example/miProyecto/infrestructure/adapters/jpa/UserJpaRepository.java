package com.example.miProyecto.infrestructure.adapters.jpa;

import com.example.miProyecto.infrestructure.adapters.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository
    extends JpaRepository<UserEntity, Long> {
}
