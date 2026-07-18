package com.files.Agents_Scheduling_MCP.agent;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.files.Agents_Scheduling_MCP.agent.dtos.CallUserRequestDto;
import com.files.Agents_Scheduling_MCP.agent.dtos.AgentResponseResponseDto;
import com.files.Agents_Scheduling_MCP.events.UserRequestEvent;
import com.files.Agents_Scheduling_MCP.services.MemoryClassify;

@RestController
@RequestMapping("/agent")
public class AgentsController {

    private final MemoryClassify memoryClassify;

    public AgentsController(MemoryClassify memoryClassify) {
        this.memoryClassify = memoryClassify;
    }

    @PostMapping
    public ResponseEntity<AgentResponseResponseDto> handleAgentResponse(@RequestBody CallUserRequestDto dto) {
        UserRequestEvent userRequestEvent = new UserRequestEvent(dto.userMessage());
        String botResponse = memoryClassify.memoryClassify(userRequestEvent);
        AgentResponseResponseDto responseDto = new AgentResponseResponseDto(botResponse);

        return ResponseEntity.ok().body(responseDto);
    }
}
