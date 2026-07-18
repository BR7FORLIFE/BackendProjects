package com.files.Agents_Scheduling_MCP.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.files.Agents_Scheduling_MCP.agent.dtos.CallUserRequestDto;
import com.files.Agents_Scheduling_MCP.events.MemoryDecissionEvent;
import com.files.Agents_Scheduling_MCP.events.ProcessingUserRequest;
import com.files.Agents_Scheduling_MCP.events.UserRequestEvent;

/**
 * Este servicio nos permititira clasificar el tipo de prompt que nos digita el
 * usuario
 * para utilizar el tipo de memoria correspondiente
 * 
 * Los tipos de clasificacion de los prompts son:
 * 
 * - conversation
 * - semantics
 * - episodic
 * - procedural
 * - job
 * - task
 * - reflective
 * 
 * //primero clasificamos y despues llamamos el orchestator para general el
 * siguiente flujo
 */
@Service
public class MemoryClassify {

    private final ChatClient chatClient;
    private final String SYSTEM_CLASIFICATION_PROMPT = """
            I want you to take the user's message and according to the message they gave you, classify it according to this json:

            {
                "conversation": boolean ,
                "semantics": boolean,
                "episodic": boolean,
                "procedural": boolean,
                "job": boolean,
                "task": boolean,
                "reflective": boolean
            }

            note that all parameters in false indicate that you decided it was not necessary to save it in memory.
            """;
    private final Orchestator orchestator;

    public MemoryClassify(ChatClient chatClient, Orchestator orchestator) {
        this.chatClient = chatClient;
        this.orchestator = orchestator;
    }

    /**
     * Metodo donde inicia la orquestacion del agente donde siempre estaremos un
     * paso atras del modelo
     * ya sea en memoria o otra funcionalidad futura
     * 
     */
    public String memoryClassify(UserRequestEvent requestUser) {
        // llamamos al modelo con sus configuraciones en este caso el system prompt
        // para clasificar el mensaje del usuario para recurrir al tipo de memoria
        // correspondiente
        MemoryDecissionEvent decissionDraft = chatClient.prompt()
                .system(SYSTEM_CLASIFICATION_PROMPT)
                .user(requestUser.message())
                .call()
                .entity(MemoryDecissionEvent.class);

        ProcessingUserRequest userRequest = new ProcessingUserRequest(requestUser.message(), decissionDraft);

        return orchestator.processResponse(userRequest);
    }
}
