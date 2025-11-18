package com.example.miProyecto.infrestructure.adapters.jpa;

import org.springframework.stereotype.Service;

import com.example.miProyecto.domain.models.Request;
import com.example.miProyecto.infrestructure.adapters.entity.RequestEntity;

@Service
public class RequestMapper {

    public RequestEntity ofModelToEntity(Request request){
        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setEndpoint(request.getEndpoint());
        requestEntity.setHttpMethod(request.getHttpMethod());
        requestEntity.setTimestamp(request.getTimestamp());

        return requestEntity;
    }

    public Request ofEntityToModel(RequestEntity entity){
        return new Request(
                entity.getId(),
                entity.getEndpoint(),
                entity.getHttpMethod(),
                entity.getTimestamp()
        );
    }

}
