package com.files.Agents_Scheduling_MCP.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.files.Agents_Scheduling_MCP.agent.command.AgentResponseCommand;
import com.files.Agents_Scheduling_MCP.events.ProcessingContentMemory;
import com.files.Agents_Scheduling_MCP.events.ProcessingUserRequest;
import com.files.Agents_Scheduling_MCP.tools.interfaces.ToolInterface;

@Service
public class Orchestator {

    private final ApplicationEventPublisher applicationEventPublisher;

    // cliente del Modelo de IA
    private final ChatClient chatClient;

    // interfaz global con todas las herramientas predefinidas por la aplicacion
    private final ToolInterface toolInterface;

    // system prompt para configurar el modelo, sus capacidades y respuestas
    // en este caso para que extraiga lo mas importante del prompt del usuario y
    // poder guardarlo en memoria
    private final String SYSTEM_ORCHESTATOR_PROMPT = """
                    You are part of an AI agent architecture.

                    Your task is to perform two independent actions from the user's message:

                    1. Generate a natural and helpful response for the user.
                       This response will be stored in the "botResponse" field.

                    2. Extract only the information that is worth remembering for future conversations.
                       Store this in the "contentSummary" field.

                    Rules for contentSummary:
                    - Write a concise summary (one sentence if possible).
                    - Keep only long-term useful information.
                    - Include facts, preferences, goals, personal information, or stable context.
                    - Ignore greetings, filler text, temporary requests, and conversational noise.
                    - If there is nothing worth remembering, return an empty string.

                    Return ONLY a JSON object matching this structure:

                    {
                      "botResponse": "...",
                      "contentSummary": "..."
                    }

                    }
            """;

    public Orchestator(ChatClient chatClient, ToolInterface tools,
            ApplicationEventPublisher applicationEventPublisher) {
        this.chatClient = chatClient;
        this.toolInterface = tools;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public String processResponse(ProcessingUserRequest userRequest) {
        /**
         * La idea es lanzar un proceso asincrono donde las distintas implementaciones
         * de memoria
         * puedan elegir si guardar el contenido por la clasificacion del agente o caso
         * contrario no hacerlo
         */
        // idea principal del prompt del usuario
        AgentResponseCommand IAResponse = chatClient.prompt()
                .user(userRequest.contentMessage())
                .system(SYSTEM_ORCHESTATOR_PROMPT)
                .tools(toolInterface)
                .call()
                .entity(AgentResponseCommand.class);

        // creamos el evento para que los distintos tipos de memorias se encarguen de la
        // logica
        ProcessingContentMemory request = new ProcessingContentMemory(IAResponse.contentSummary(),
                userRequest.memoryDecissionEvent());

        applicationEventPublisher.publishEvent(request); // lanzamos el evento para las implementaciones de memoria

        // necesitamos mandar la respuesta del promt al usuario!!
        return IAResponse.botResponse();
    }
}
