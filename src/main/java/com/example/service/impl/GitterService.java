package com.example.service.impl;

import com.example.service.ChatService;
import com.example.service.gitter.GitterClient;
import com.example.service.gitter.dto.MessageResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GitterService implements ChatService<MessageResponse> {

    private final Flux<MessageResponse> gitterMessageSource;

    @Autowired
    public GitterService(GitterClient gitterClient) {
        //todo provide direct connection to gitter client with subsequent source publisher
        throw new UnsupportedOperationException();

//        gitterMessageSource = Flux.from(gitterClient.getMessages(null))
//                .onBackpressureBuffer()
//                .publish(1)
//                .autoConnect(0);
    }

    @Override
    @SneakyThrows
    public Flux<MessageResponse> stream() {
        return gitterMessageSource;
    }
}
