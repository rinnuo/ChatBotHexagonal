package com.example.miProyecto.application.port.out;

// import com.example.miProyecto.application.dto.RequestDto;
import com.example.miProyecto.domain.models.Request;

import java.util.List;

public interface RequestRepository {

    public List<Request> getAll();
    public Request getById(long id);
    public Request save(Request request);

}
