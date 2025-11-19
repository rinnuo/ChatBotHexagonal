package com.example.miProyecto.application.dto;

public class ChatRequestDto {
    private String prompt;

    public ChatRequestDto() {}

    public ChatRequestDto(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }
}