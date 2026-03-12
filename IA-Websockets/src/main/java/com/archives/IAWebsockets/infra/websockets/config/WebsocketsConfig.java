package com.archives.IAWebsockets.infra.websockets.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import com.archives.IAWebsockets.infra.websockets.handler.ChatWebSocketsHandler;

@Configuration
public class WebsocketsConfig { // clase de configuracion para mapear los endpoints websockets

    /**
     * esta configuracion nos permite mappear las rutas y definir que handler se va
     * a ejecutar
     * segun el path, por ejemplo en el caso de este bean, cuando el cliente haga la
     * peticion
     * a la ruta /chat va a usar el handler ChatWebSocketHandler
     * 
     */
    @Bean
    public HandlerMapping websocketsMapping(ChatWebSocketsHandler handler) {

        // definimos un objeto o map de configuracion donde en el primer valor
        // va el path y en el segundo argumento ira una implementacion de tipo
        // WebSocketHandler
        Map<String, WebSocketHandler> mapperPaths = new HashMap<>();

        mapperPaths.put("/chat", handler);

        // implementacion de HandlerMapping
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setUrlMap(mapperPaths); // definimos que url se mapeará

        return mapping;
    }

}
