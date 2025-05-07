package fr.morgan.initiativeasso.service.interfaces;

import fr.morgan.initiativeasso.model.Message;
import fr.morgan.initiativeasso.model.dto.MessageDto;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.repository.MessageRepository;

import java.util.Optional;

public interface MessageService {

    Message saveMessage(MessageDto messageDto) throws UserNotFoundException;
    Optional<Message> getMessageById(Long id);
}
