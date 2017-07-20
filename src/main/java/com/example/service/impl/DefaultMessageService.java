package com.example.service.impl;

import com.example.controller.vm.MessageVM;
import com.example.domain.Message;
import com.example.repository.MessageRepository;
import com.example.service.ChatService;
import com.example.service.MessageService;
import com.example.service.gitter.dto.MessageResponse;
import com.example.service.impl.utils.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class DefaultMessageService implements MessageService {
    private final ChatService<MessageResponse> chatClient;

    @Autowired
    public DefaultMessageService(MessageRepository messageRepository,
                                 ChatService<MessageResponse> chatClient,
                                 MessageBroker messageBroker) {
        this.chatClient = chatClient;

        Flux<Message> saved = messageRepository.saveAll(chatClient.stream().transform(MessageMapper::toDomainUnits));

        messageBroker.createChannel("statisticChanged", saved.materialize());
    }

    @Override
    public Flux<MessageVM> latest() {
        //todo provide message emitting to downstream
        throw new UnsupportedOperationException();

//        return chatClient.stream().transform(MessageMapper::toViewModelUnits);
    }
}
