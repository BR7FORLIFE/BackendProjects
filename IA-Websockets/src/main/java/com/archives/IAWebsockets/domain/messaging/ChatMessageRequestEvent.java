package com.archives.IAWebsockets.domain.messaging;

public final class ChatMessageRequestEvent {

    private final String sessionId;
    private final String message;

    public ChatMessageRequestEvent(String sessionId, String message) {
        this.sessionId = sessionId;
        this.message = message;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getMessage() {
        return message;
    }
}
