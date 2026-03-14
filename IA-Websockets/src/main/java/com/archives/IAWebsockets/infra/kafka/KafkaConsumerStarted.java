package com.archives.IAWebsockets.infra.kafka;

import org.springframework.stereotype.Component;

import com.archives.IAWebsockets.application.IAchat.usecases.IAChatUseCase;

import jakarta.annotation.PostConstruct;

@Component
public class KafkaConsumerStarted {

    private final IAChatUseCase iaChatUseCase;

    public KafkaConsumerStarted(IAChatUseCase iaChatUseCase) {
        this.iaChatUseCase = iaChatUseCase;
    }

    @PostConstruct
    public void start() {
        iaChatUseCase.startedConsumed().subscribe();
    }
}
