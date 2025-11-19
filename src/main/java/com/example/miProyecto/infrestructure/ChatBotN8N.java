package com.example.miProyecto.infrestructure;

import com.example.miProyecto.application.port.ChatBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component("n8n")
public class ChatBotN8N implements ChatBot {

    @Value("${chatbot.n8n.url}")
    private String n8nUrl;

    @Value("${chatbot.n8n.apiKey:}")
    private String apiKey;

    private final RestTemplate rest = new RestTemplate();

    @Override
    public String message(String prompt) {
        if (n8nUrl == null || n8nUrl.isBlank()) {
            return "n8n URL not configured";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (apiKey != null && !apiKey.isBlank()) {
            headers.set("Authorization", "Bearer " + apiKey);
        }

        Map<String, Object> body = Map.of("prompt", prompt);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> resp = rest.postForEntity(n8nUrl, request, String.class);
            if (resp.getStatusCode().is2xxSuccessful() && resp.getBody() != null) {
                return resp.getBody();
            }
            return "n8n responded with status: " + resp.getStatusCodeValue();
        } catch (Exception e) {
            return "n8n call failed: " + e.getMessage();
        }
    }
}
