package com.files.Agents_Scheduling_MCP.events;

public record MemoryDecissionEvent(
        Boolean conversation,
        Boolean semantics,
        Boolean episodic,
        Boolean procedural,
        Boolean job,
        Boolean task,
        Boolean reflective) {

}
