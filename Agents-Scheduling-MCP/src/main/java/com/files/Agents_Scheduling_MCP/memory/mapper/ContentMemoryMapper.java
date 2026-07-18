package com.files.Agents_Scheduling_MCP.memory.mapper;

import com.files.Agents_Scheduling_MCP.memory.entities.ContentMemoryEntity;
import com.files.Agents_Scheduling_MCP.memory.models.ContentMemoryModel;

public class ContentMemoryMapper {

    public static ContentMemoryModel toDomain(ContentMemoryEntity contentMemoryEntity) {
        return ContentMemoryModel.createNew(
                contentMemoryEntity.getId(),
                contentMemoryEntity.getContent(),
                contentMemoryEntity.getCreateAt());
    }

    public static ContentMemoryEntity toEntity(ContentMemoryModel contentMemoryModel) {
        return ContentMemoryEntity.builder()
                .id(contentMemoryModel.getId())
                .content(contentMemoryModel.getContent())
                .createAt(contentMemoryModel.getCreateAt())
                .build();
    }
}
