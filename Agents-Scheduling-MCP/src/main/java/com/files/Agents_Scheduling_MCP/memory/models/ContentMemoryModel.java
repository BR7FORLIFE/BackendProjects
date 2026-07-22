package com.files.Agents_Scheduling_MCP.memory.models;

import java.time.Instant;
import java.util.UUID;

public class ContentMemoryModel {
    private final UUID id;
    private final String content;
    private final Instant createAt;

    private ContentMemoryModel(UUID id, String content, Instant createAt) {
        this.id = id;
        this.content = content;
        this.createAt = createAt;
    }

    public static ContentMemoryModel createDratf(String content) {
        return new ContentMemoryModel(null, content, Instant.now());
    }

    public static ContentMemoryModel createNew(UUID id, String content, Instant createAt) {
        return new ContentMemoryModel(id, content, createAt);
    }

    public UUID getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Instant getCreateAt() {
        return createAt;
    }
}
