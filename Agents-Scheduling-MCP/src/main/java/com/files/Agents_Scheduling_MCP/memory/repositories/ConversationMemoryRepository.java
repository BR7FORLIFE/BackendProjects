package com.files.Agents_Scheduling_MCP.memory.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.files.Agents_Scheduling_MCP.memory.entities.ContentMemoryEntity;

public interface ConversationMemoryRepository extends JpaRepository<ContentMemoryEntity, UUID> {

}
