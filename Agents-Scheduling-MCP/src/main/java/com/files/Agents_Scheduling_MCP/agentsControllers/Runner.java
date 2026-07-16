package com.files.Agents_Scheduling_MCP.agentsControllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.files.Agents_Scheduling_MCP.AI.tools.DirectoryTool;

@Configuration
public class Runner {

    // @Bean
    // public CommandLineRunner commandLineRunner(ChatClient chatClient, DirectoryTool directoryTool) {
    //     return args -> {
    //         chatClient.prompt("""
    //                     Lists the folders within a directory
    //                 """).tools(directoryTool).call();
    //     };
    // }
}
