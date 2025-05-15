package fr.morgan.initiativeasso.service;

import fr.morgan.initiativeasso.model.Message;
import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.dto.MessageDto;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.repository.MessageRepository;
import fr.morgan.initiativeasso.repository.UserRepository;
import fr.morgan.initiativeasso.service.interfaces.MessageService;
import fr.morgan.initiativeasso.service.mapper.MessageMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageMapper messageMapper;

    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.messageMapper = messageMapper;
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

        return messageRepository.save(message);
    }

    @Override
    public List<MessageDto> findMessagesByReceiverId(Long receiverId) throws UserNotFoundException {
        List<Message> listeMsg = messageRepository.findByReceiverId(receiverId);
        return messageMapper.toDtoList(listeMsg);
    }

    @Override
    public Map<Long, List<MessageDto>> getConversationByUserId(Long id) {
        List<MessageDto> messages = messageMapper.toDtoList(messageRepository.findUserConversations(id));

        return messages.stream()
                .filter(msg -> msg.getSenderId().equals(id) || msg.getReceiverId().equals(id))
                .collect(Collectors.groupingBy(msg -> {
                    return msg.getSenderId().equals(id)
                            ? msg.getReceiverId()
                            : msg.getSenderId();
                }));
    }
}