package com.example.miProyecto.infrestructure.controllers;

import com.example.miProyecto.application.services.ChatBotService;
import com.example.miProyecto.application.dto.ChatRequestDto;
import com.example.miProyecto.application.dto.ChatResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatBotService service;
    private final ObjectMapper mapper = new ObjectMapper();

    public ChatController(ChatBotService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ChatResponseDto> chat(@RequestBody ChatRequestDto request) {
        try {
            Map<String, String> body = Map.of(
                "sessionId", request.getSessionId() == null ? "" : request.getSessionId(),
                "chatInput", request.getChatInput() == null ? "" : request.getChatInput()
            );
            String json = mapper.writeValueAsString(body);
            String result = service.message(json, request.getProvider());
            return ResponseEntity.ok(new ChatResponseDto(result));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ChatResponseDto("error: " + e.getMessage()));
        }
    }
}