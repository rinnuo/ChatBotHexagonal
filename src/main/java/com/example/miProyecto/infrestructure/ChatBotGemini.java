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

    @Value("${chatbot.gemini.apiKey:}")
    private String apiKey;

    private final RestTemplate rest = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String message(String prompt) {
        if (geminiUrl == null || geminiUrl.isBlank()) {
            return "Gemini URL not configured";
        }

        // Determine text to send: extract chatInput if prompt is JSON {sessionId, chatInput}
        String textToSend = prompt;
        try {
            String trimmed = prompt == null ? "" : prompt.trim();
            if (trimmed.startsWith("{")) {
                JsonNode node = mapper.readTree(prompt);
                if (node.has("chatInput")) {
                    textToSend = node.get("chatInput").asText("");
                } else {
                    textToSend = prompt;
                }
            }
        } catch (Exception ignored) {}

        // Build Gemini request body: { "contents": [ { "parts": [ { "text": "..." } ] } ] }
        Object body = Map.of(
            "contents", new Object[] {
                Map.of("parts", new Object[] { Map.of("text", textToSend) })
            }
        );

        String bodyJson;
        try {
            bodyJson = mapper.writeValueAsString(body);
        } catch (Exception e) {
            return "failed to build Gemini request: " + e.getMessage();
        }

        // Build final URL: append ?key=API_KEY if apiKey present and not already in URL
        String finalUrl = geminiUrl;
        if (apiKey != null && !apiKey.isBlank()) {
            if (!finalUrl.contains("?")) {
                finalUrl = finalUrl + "?key=" + apiKey;
            } else if (!finalUrl.contains("key=")) {
                finalUrl = finalUrl + "&key=" + apiKey;
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(bodyJson, headers);

        try {
            ResponseEntity<String> resp = rest.postForEntity(finalUrl, request, String.class);
            if (resp.getStatusCode().is2xxSuccessful() && resp.getBody() != null) {
                try {
                    JsonNode root = mapper.readTree(resp.getBody());
                    // expected: candidates[0].content.parts[0].text
                    if (root.has("candidates") && root.get("candidates").isArray() && root.get("candidates").size() > 0) {
                        JsonNode first = root.get("candidates").get(0);
                        if (first.has("content")) {
                            JsonNode content = first.get("content");
                            if (content.has("parts") && content.get("parts").isArray() && content.get("parts").size() > 0) {
                                JsonNode part0 = content.get("parts").get(0);
                                if (part0.has("text")) {
                                    return part0.get("text").asText();
                                }
                            }
                        }
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
