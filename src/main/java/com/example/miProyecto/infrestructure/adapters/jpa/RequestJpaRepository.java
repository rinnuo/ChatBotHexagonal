package com.example.miProyecto.infrestructure.adapters.jpa;

import com.example.miProyecto.infrestructure.adapters.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestJpaRepository extends JpaRepository<RequestEntity, Long> {
}
