package com.files.Agents_Scheduling_MCP.memory.repositories;

import java.util.List;

import org.springframework.stereotype.Service;

import com.files.Agents_Scheduling_MCP.memory.entities.ContentMemoryEntity;
import com.files.Agents_Scheduling_MCP.memory.mapper.ContentMemoryMapper;
import com.files.Agents_Scheduling_MCP.memory.models.ContentMemoryModel;
import com.files.Agents_Scheduling_MCP.memory.ports.MemoryRepositoryPort;

@Service
public class ConversationMemoryRepositoryAdapter implements MemoryRepositoryPort<ContentMemoryModel> {
    private final ConversationMemoryRepository conversationMemoryRepository;

    public ConversationMemoryRepositoryAdapter(ConversationMemoryRepository conversationMemoryRepository) {
        this.conversationMemoryRepository = conversationMemoryRepository;
    }

    public ContentMemoryModel save(ContentMemoryModel contentMemoryModel) {
        ContentMemoryEntity saved = conversationMemoryRepository.save(ContentMemoryMapper.toEntity(contentMemoryModel));

        return ContentMemoryMapper.toDomain(saved);
    }

    @Override
    public List<ContentMemoryModel> findAll() {
        return conversationMemoryRepository.findAll().stream().map(ContentMemoryMapper::toDomain).toList();
    }
}
