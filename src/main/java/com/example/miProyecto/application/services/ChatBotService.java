package com.example.miProyecto.application.services;

import com.example.miProyecto.application.port.out.ChatBot;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ChatBotService {

    private final ChatBot chatBot;

    public ChatBotService(@Qualifier("n8n") ChatBot chatBot) {
        this.chatBot = chatBot;
    }

    public String message(String prompt) {
        return chatBot.message(prompt);
    }
}