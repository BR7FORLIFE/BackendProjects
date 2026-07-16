package com.files.Agents_Scheduling_MCP.orchestator;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import com.files.Agents_Scheduling_MCP.AI.tools.DirectoryTool;
import com.files.Agents_Scheduling_MCP.orchestator.dtos.CallUserRequestDto;
import com.files.Agents_Scheduling_MCP.orchestator.dtos.CallUserResponseDto;

@Service
public class Orchestator {

    private final ChatClient chatClient;
    private final DirectoryTool directoryTool;

    public Orchestator(ChatClient.Builder chatClientBuilder, DirectoryTool directoryTool) {
        this.chatClient = chatClientBuilder.build();
        this.directoryTool = directoryTool;
    }

    public CallUserResponseDto sendResponse(CallUserRequestDto dto) {
        PromptTemplate pt = new PromptTemplate(dto.userMessage());

        String response = chatClient.prompt(pt.create())
                .tools(directoryTool)
                .call()
                .content();

        return new CallUserResponseDto(response);
    }
}
