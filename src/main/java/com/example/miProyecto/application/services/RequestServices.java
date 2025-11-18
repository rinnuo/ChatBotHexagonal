package com.example.miProyecto.application.services;

import com.example.miProyecto.application.port.out.RequestRepository;
import com.example.miProyecto.domain.models.Request;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServices {
    private final RequestRepository requestRepository;

    public RequestServices(RequestRepository requestRepository){
        this.requestRepository = requestRepository;
    }


    public List<Request> getAll(){
        return requestRepository.getAll();
    }

    public Request getById(long id){
        return requestRepository.getById(id);
    }

    public Request save(Request request){
        return requestRepository.save(request);
    }

}
