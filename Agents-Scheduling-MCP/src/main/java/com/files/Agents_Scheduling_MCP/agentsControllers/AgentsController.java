package com.files.Agents_Scheduling_MCP.agentsControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.files.Agents_Scheduling_MCP.orchestator.Orchestator;
import com.files.Agents_Scheduling_MCP.orchestator.dtos.CallUserRequestDto;
import com.files.Agents_Scheduling_MCP.orchestator.dtos.CallUserResponseDto;

@RestController
@RequestMapping("/agent")
public class AgentsController {

    private final Orchestator orchestator;

    public AgentsController(Orchestator orchestator) {
        this.orchestator = orchestator;
    }

    @PostMapping
    public ResponseEntity<CallUserResponseDto> handleAgentResponse(@RequestBody CallUserRequestDto dto) {
        CallUserResponseDto res = this.orchestator.sendResponse(dto);
        return ResponseEntity.ok().body(res);
    }
}
