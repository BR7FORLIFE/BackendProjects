package com.files.Agents_Scheduling_MCP.agentsControllers;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

class User {
    public String name;
    public Integer age;
}

@RestController
@RequestMapping("/test")
public class TestController {

    private final ChatClient chatClient;

    public TestController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping
    public List<User> getAllUserTest() {
        PromptTemplate pt = new PromptTemplate("""
                    Return a current list of 10 persons if exists or generate a new list with random values.
                """);

        return this.chatClient.prompt(pt.create())
                .call()
                // permite mantener los tipos en tiempo de ejecucion
                .entity(new ParameterizedTypeReference<List<User>>() {
                });
    }
}
