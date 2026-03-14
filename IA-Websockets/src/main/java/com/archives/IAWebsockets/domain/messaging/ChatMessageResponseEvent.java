package com.archives.IAWebsockets.domain.messaging;

public final class ChatMessageResponseEvent {
    private final String sessionId;
    private final String response;

    public ChatMessageResponseEvent(String sessionId, String response) {
        this.sessionId = sessionId;
        this.response = response;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getResponse() {
        return response;
    }
}
