package com.example.miProyecto.infrestructure;

import com.example.miProyecto.application.port.out.ChatBot;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component("gemini")
public class ChatBotGemini implements ChatBot {

    @Value("${chatbot.gemini.url}")
    private String geminiUrl;

    // apiKey removed - sending to local proxy

    private final RestTemplate rest = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String message(String prompt) {
        if (geminiUrl == null || geminiUrl.isBlank()) {
            return "Gemini URL not configured";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String bodyJson;
        try {
            String trimmed = prompt == null ? "" : prompt.trim();
            if (trimmed.startsWith("{")) {
                bodyJson = prompt;
            } else {
                bodyJson = mapper.writeValueAsString(Map.of("sessionId", "", "chatInput", prompt));
            }
        } catch (Exception e) {
            bodyJson = "{\"sessionId\":\"\",\"chatInput\":\"" + (prompt == null ? "" : prompt) + "\"}";
        }

        HttpEntity<String> request = new HttpEntity<>(bodyJson, headers);

        try {
            ResponseEntity<String> resp = rest.postForEntity(geminiUrl, request, String.class);
            if (resp.getStatusCode().is2xxSuccessful() && resp.getBody() != null) {
                // try to extract "output" field (same contract as n8n)
                try {
                    JsonNode node = mapper.readTree(resp.getBody());
                    if (node.has("output")) {
                        return node.get("output").asText();
                    }
                } catch (Exception ignore) {}
                return resp.getBody();
            }
            return "Gemini responded with status: " + resp.getStatusCodeValue();
        } catch (Exception e) {
            return "Gemini call failed: " + e.getMessage();
        }
    }
}
