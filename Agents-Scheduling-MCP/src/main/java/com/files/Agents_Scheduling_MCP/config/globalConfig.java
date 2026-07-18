package com.files.Agents_Scheduling_MCP.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync // activamos la asincronia en el proyecto para procesar eventos
@Configuration
public class globalConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatBuilder) {
        return chatBuilder.build();
    }
}
