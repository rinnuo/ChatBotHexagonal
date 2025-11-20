package com.example.miProyecto.application.dto;

public class ChatRequestDto {
    private String sessionId;
    private String chatInput;

    public ChatRequestDto() {}

    public ChatRequestDto(String sessionId, String chatInput) {
        this.sessionId = sessionId;
        this.chatInput = chatInput;
    }

    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }

    public String getChatInput() { return chatInput; }
    public void setChatInput(String chatInput) { this.chatInput = chatInput; }
}