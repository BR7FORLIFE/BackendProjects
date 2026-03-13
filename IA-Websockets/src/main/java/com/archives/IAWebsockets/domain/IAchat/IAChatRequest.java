package com.archives.IAWebsockets.domain.IAchat;

public final class IAChatRequest {

    private final String clientMessage;

    public IAChatRequest(String clientMessage) {
        this.clientMessage = clientMessage;
    }

    public String getClientMessage() {
        return clientMessage;
    }
}
