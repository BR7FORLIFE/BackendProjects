package com.files.Agents_Scheduling_MCP.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.files.Agents_Scheduling_MCP.events.MemoryDecissionEvent;
import com.files.Agents_Scheduling_MCP.events.ProcessingUserRequest;
import com.files.Agents_Scheduling_MCP.events.UserRequestEvent;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j // LOMBOK en ves de hacer Logger log = LoggerFactory.getLogger(<CLASE>.class)
@Service
public class MemoryClassify {

    private final ChatClient chatClient;
    private final String SYSTEM_CLASIFICATION_PROMPT = """
                        You are a memory classification engine.

            Your task is to determine whether the user's latest message contains information that should be stored in one or more long-term memory categories.

            ## Memory Categories

            ### conversation
            Set to true if the message contains temporary context that is useful only for the current conversation.

            Examples:
            - "Let's use Java in this example."
            - "For this conversation, pretend you are a senior architect."

            Otherwise set to false.

            ---

            ### semantics
            Set to true if the message contains stable facts about the user that are likely to remain true over time.

            Examples:
            - Profession
            - Skills
            - Languages
            - Interests
            - Preferences
            - Hobbies
            - Long-term personal information

            Examples:
            - "I'm a backend developer."
            - "I speak Spanish."
            - "I like Formula 1."

            Otherwise set to false.

            ---

            ### episodic
            Set to true if the message describes something the user experienced or something that happened to them.

            Examples:
            - "Yesterday I deployed my first application."
            - "Last week I passed the AWS exam."

            Otherwise set to false.

            ---

            ### procedural
            Set to true if the user is defining how the assistant should behave in future conversations.

            Examples:
            - "Always answer in Spanish."
            - "Explain everything step by step."
            - "Be my senior backend mentor."

            Otherwise set to false.

            ---

            ### job
            Set to true if the message mentions an ongoing project, occupation, study, responsibility, or long-term objective.

            Examples:
            - "I'm studying AWS Cloud Practitioner."
            - "I'm building an AI agent."
            - "I'm working on a NestJS backend."

            Otherwise set to false.

            ---

            ### task
            Set to true if the user is describing an active task, request, reminder, or piece of work that may require follow-up.

            Examples:
            - "Help me finish this API."
            - "Tomorrow we need to implement authentication."

            Otherwise set to false.

            ---

            ### reflective
            Set to true if the user expresses motivations, goals, values, opinions, aspirations, or personal reflections.

            Examples:
            - "I want to become a better engineer."
            - "I enjoy solving difficult problems."

            Otherwise set to false.

            ---

            ## Classification Rules

            - Multiple categories may be true.
            - If a category does not clearly apply, set it to false.
            - Never infer facts that the user did not explicitly state.
            - Ignore greetings, jokes, acknowledgements, casual conversation, and generic questions.
            - Classify ONLY the latest user message.

            ## Response Format

            Return ONLY valid JSON.

            Use exactly this schema:

            {
                "conversation": boolean,
                "semantic": boolean,
                "episodic": boolean,
                "procedural": boolean,
                "job": boolean,
                "task": boolean,
                "reflective": boolean
            }

            Do not include explanations.
            Do not include markdown.
            Do not include any text outside the JSON.
                        """;
    private final Orchestator orchestator;

    @Value("${spring.ai.ollama.chat.model}")
    private String SELECTED_MODEL;

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
        log.info("---Servicio de clasificacion de memoria---");
        log.debug("Invocando el modelo para la generacion..");
        log.debug("Prompt del usuario: {}", requestUser.message());
        Long startTime = System.currentTimeMillis();

        MemoryDecissionEvent decissionDraft = chatClient.prompt()
                .system(SYSTEM_CLASIFICATION_PROMPT)
                .user(requestUser.message())
                .call()
                .entity(MemoryDecissionEvent.class);

        Long endTime = System.currentTimeMillis() - startTime;

        log.info("Modelo {} Tiempo de ejecucion {} ms", SELECTED_MODEL, endTime);
        log.info(
                "Clasificacion de memoria completada. conversation={} semantic={} episodic={} procedural={} job={} task={} reflective={}",
                decissionDraft.conversation(),
                decissionDraft.semantic(),
                decissionDraft.episodic(),
                decissionDraft.procedural(),
                decissionDraft.job(),
                decissionDraft.task(),
                decissionDraft.reflective());

        log.info("Finalizacion de clasificacion de memoria");

        ProcessingUserRequest userRequest = new ProcessingUserRequest(requestUser.message(), decissionDraft);

        return orchestator.processResponse(userRequest);
    }
}
