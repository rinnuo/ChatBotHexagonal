package com.example.miProyecto.infrestructure.adapters.jpa;

import com.example.miProyecto.application.port.out.RequestRepository;
import com.example.miProyecto.domain.models.Request;
import com.example.miProyecto.infrestructure.adapters.entity.RequestEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class RequestRepositoryAdapter implements RequestRepository {
    private RequestJpaRepository requestJpaRepository;
    private RequestMapper requestMapper;

    @Override
    public List<Request> getAll() {
        return requestJpaRepository.findAll().stream().map( entity ->
            requestMapper.ofEntityToModel(entity)
        ).toList();
    }

    @Override
    public Request getById(long id) {
        return requestMapper.ofEntityToModel(
                requestJpaRepository.findById(id).get()
        );
    }

    @Override
    public Request save(Request request) {
        RequestEntity entity = requestJpaRepository.save(
                requestMapper.ofModelToEntity(request)
        );
        return requestMapper.ofEntityToModel(entity);
    }
}
