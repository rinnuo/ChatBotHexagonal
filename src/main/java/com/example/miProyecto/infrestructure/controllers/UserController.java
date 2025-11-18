package com.example.miProyecto.infrestructure.controllers;


import com.example.miProyecto.application.dto.UserDto;
import com.example.miProyecto.application.services.UserServices;
import com.example.miProyecto.application.services.RequestServices;
import com.example.miProyecto.domain.models.User;
import com.example.miProyecto.domain.models.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServices userServices;
    private final RequestServices requestServices;


    @GetMapping
    public List<UserDto> getAll(){
        requestServices.save(new Request(0, "/api/user", "GET", null));
        return userServices.getAll();
    }

    @GetMapping("/{id}")
		public UserDto getById(@PathVariable long id){
            requestServices.save(new Request(0, "/api/user/" + id, "GET", null));
			return userServices.getById(id);
		}

    @PostMapping
    public UserDto save(@RequestBody User user){
        requestServices.save(new Request(0, "/api/user", "POST", null));
        return userServices.save(user);
    }

    @PostMapping("/{id}")
    public UserDto update(@PathVariable long id){
        requestServices.save(new Request(0, "/api/user/" + id, "POST", null));
        return userServices.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        userServices.delete(id);
        requestServices.save(new Request(0, "/api/user/" + id, "DELETE", null));
    }
}
