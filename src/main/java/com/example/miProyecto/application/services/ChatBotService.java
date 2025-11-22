package com.example.miProyecto.application.services;

import com.example.miProyecto.application.port.out.ChatBot;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChatBotService {

    private final Map<String, ChatBot> chatBots;
    private final String defaultProvider = "n8n";

    public ChatBotService(Map<String, ChatBot> chatBots) {
        this.chatBots = chatBots;
    }

    public String message(String prompt) {
        return message(prompt, defaultProvider);
    }

    public String message(String prompt, String provider) {
        String key = (provider == null || provider.isBlank()) ? defaultProvider : provider;
        ChatBot bot = chatBots.get(key);
        if (bot == null) {
            return "Chat provider not available: " + key;
        }
        return bot.message(prompt);
    }
}