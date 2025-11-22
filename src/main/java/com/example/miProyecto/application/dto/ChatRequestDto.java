package com.example.miProyecto.application.dto;

public class ChatRequestDto {
    private String sessionId;
    private String chatInput;
    private String provider; // optional: "n8n" or "gemini"

    public ChatRequestDto() {}

    public ChatRequestDto(String sessionId, String chatInput) {
        this.sessionId = sessionId;
        this.chatInput = chatInput;
    }

    public ChatRequestDto(String sessionId, String chatInput, String provider) {
        this.sessionId = sessionId;
        this.chatInput = chatInput;
        this.provider = provider;
    }

    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }

    public String getChatInput() { return chatInput; }
    public void setChatInput(String chatInput) { this.chatInput = chatInput; }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }
}