package com.files.LiveProductLive.infra.websockets.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import com.files.LiveProductLive.infra.websockets.handler.StatsHandler;

@Configuration
public class WebSocketsConfig {

    // la interfaz HandlerMapping pretente unir un path (websockets) con su
    // respectivo handler
    @Bean
    public HandlerMapping webSocketsMapping(
            StatsHandler handler) {

        // constrimos el mapa de paths -> handler asociado
        Map<String, WebSocketHandler> config = new HashMap<>();
        config.put("/stats", handler); // path de ranking de vistas de productos en tiempo real

        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setUrlMap(config);
        mapping.setOrder(-1); // el orden de mayor prioridad frente otros mapeadores de la aplicacion

        return mapping;
    }
}
