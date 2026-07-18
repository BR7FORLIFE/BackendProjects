package com.files.Agents_Scheduling_MCP.memory;

import java.util.List;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.files.Agents_Scheduling_MCP.events.ProcessingContentMemory;
import com.files.Agents_Scheduling_MCP.events.ProcessingUserRequest;
import com.files.Agents_Scheduling_MCP.memory.models.ContentMemoryModel;
import com.files.Agents_Scheduling_MCP.memory.ports.AgentMemoryPort;
import com.files.Agents_Scheduling_MCP.memory.ports.ConversationMemoryInterface;
import com.files.Agents_Scheduling_MCP.memory.ports.MemoryRepositoryPort;

/**
 * 
 * ConversationMemoryImp
 * 
 * Funcionalidad de esta clase
 * 
 * 1. contruir el content memory model
 * 2. guardarlo en la base de datos como memoria del modelo
 * 3. retornar en este caso si se requiere la lista de content de la memoria
 * conversation
 */
@Service
public class ConversationMemoryImp extends AgentMemoryPort<ContentMemoryModel> implements ConversationMemoryInterface {

    private final MemoryRepositoryPort<ContentMemoryModel> memoryRepositoryPort;

    public ConversationMemoryImp(MemoryRepositoryPort<ContentMemoryModel> memoryRepositoryPort) {
        this.memoryRepositoryPort = memoryRepositoryPort;
    }

    @Async
    @EventListener
    @Override
    protected void save(ProcessingContentMemory processingContentMemory) {
        if (!processingContentMemory.memoryDecissionEvent().conversation()) {
            return;
        }

        ContentMemoryModel newModel = ContentMemoryModel.createDratf(processingContentMemory.contentMessage());
        memoryRepositoryPort.save(newModel); // guardamos en la memoria
    }

    @Override
    protected List<ContentMemoryModel> getContentMemory() {
        return memoryRepositoryPort.findAll();
    }
}
