package com.example.miProyecto.application.dto;

public class ChatResponseDto {
    private String output;

    public ChatResponseDto() {}
    public ChatResponseDto(String output) { this.output = output; }

    public String getOutput() { return output; }
    public void setOutput(String output) { this.output = output; }
}