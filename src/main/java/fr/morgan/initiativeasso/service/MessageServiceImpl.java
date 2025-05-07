package fr.morgan.initiativeasso.service;

import fr.morgan.initiativeasso.model.Message;
import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.dto.MessageDto;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.repository.MessageRepository;
import fr.morgan.initiativeasso.repository.UserRepository;
import fr.morgan.initiativeasso.service.interfaces.MessageService;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Message saveMessage(MessageDto messageDto) throws UserNotFoundException {

        User sender = userRepository.findById(messageDto.getSenderId()).orElseThrow(() -> new UserNotFoundException("User not found"));
        User receiver = userRepository.findById(messageDto.getReceiverId()).orElseThrow(() -> new UserNotFoundException("User not found"));

                Message message = Message.builder()
                        .text(messageDto.getText())
                        .read(false)
                        .sender(sender)
                        .receiver(receiver)
                        .timeStamp(LocalDateTime.now())
                        .build();

        log.warn("Sender: " + sender.getUsername() + ", ID: " + sender.getId());
        log.warn("Sender via Message: " + message.getSender().getUsername() + ", ID: " + message.getSender().getId());
        log.warn("Receiver: " + receiver.getUsername() + ", ID: " + receiver.getId());
        log.warn("Receiver via Message: " + message.getReceiver().getUsername() + ", ID: " + message.getReceiver().getId());
        log.warn("Text: " + message.getText());

        return messageRepository.save(message);
    }

    @Override
    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }
}