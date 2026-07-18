package com.files.Agents_Scheduling_MCP.memory.ports;

import java.util.List;

public interface MemoryRepositoryPort<T> {
    T save(T model);

    List<T> findAll();
}
