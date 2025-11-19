package com.example.miProyecto.infrestructure.controllers;

import com.example.miProyecto.application.services.ChatBotService;
import com.example.miProyecto.application.dto.ChatRequestDto;
import com.example.miProyecto.application.dto.ChatResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatBotService service;

    public ChatController(ChatBotService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ChatResponseDto> chat(@RequestBody ChatRequestDto request) {
        String result = service.message(request.getPrompt());
        return ResponseEntity.ok(new ChatResponseDto(result));
    }
}