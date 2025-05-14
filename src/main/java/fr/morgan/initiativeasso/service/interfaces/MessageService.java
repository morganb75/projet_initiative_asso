package fr.morgan.initiativeasso.service.interfaces;

import fr.morgan.initiativeasso.model.Message;
import fr.morgan.initiativeasso.model.dto.MessageDto;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.repository.MessageRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MessageService {

    Message saveMessage(MessageDto messageDto) throws UserNotFoundException;
    List<MessageDto> findMessagesByReceiverId (Long receiverId) throws UserNotFoundException;
//    List<MessageDto> findMessagesByReceiverIdAndSenderId (Long receiverId, Long senderId) throws UserNotFoundException;
    Map<Long, List<MessageDto>> getConversationByUserId(Long id);
}
