package com.files.Agents_Scheduling_MCP.memory.ports;

import java.util.List;

import com.files.Agents_Scheduling_MCP.events.ProcessingContentMemory;

// interfaz global donde se encuentra todas las implementaciones de memoria con
// un metodo en comun save() y findAll()
public abstract class AgentMemoryPort<T> {
    // metodo de implementacion para guardar en la memoria segun el tipo
    protected abstract void save(ProcessingContentMemory processingContentMemory);

    protected abstract List<T> getContentMemory();
}

/**
 * 
 * public void conversationMemory()
 * 
 * 
 * public void semanticsMemory()
 * 
 * 
 * public void episodicMemory()
 * 
 * 
 * public void proceduralMemory()
 *
 * 
 * public void jobMemory()
 * 
 * 
 * public void taskMemory()
 * 
 * 
 * public void reflectiveMemory()
 * 
 */