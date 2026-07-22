package com.files.Agents_Scheduling_MCP.events;

public record MemoryDecissionEvent(
        Boolean conversation,
        Boolean semantic,
        Boolean episodic,
        Boolean procedural,
        Boolean job,
        Boolean task,
        Boolean reflective) {

}
