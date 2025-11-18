package com.example.miProyecto.infrestructure.controllers;

import com.example.miProyecto.application.services.RequestServices;
import com.example.miProyecto.domain.models.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/request")
@RequiredArgsConstructor
public class RequestController {
    private final RequestServices requestServices;


    @GetMapping
    public List<Request> getAll(){
        return requestServices.getAll();
    }

	@GetMapping("/{id}")
	public Request getById(@PathVariable long id){
		return requestServices.getById(id);
	}

    @PostMapping
    public Request save(@RequestBody Request request){
        return requestServices.save(request);
    }

    @PostMapping("/{id}")
    public Request update(@PathVariable long id){
        return requestServices.getById(id);
    }
}
