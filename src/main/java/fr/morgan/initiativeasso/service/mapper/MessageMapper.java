package fr.morgan.initiativeasso.service.mapper;

import fr.morgan.initiativeasso.model.Message;
import fr.morgan.initiativeasso.model.dto.MessageDto;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(target="senderId", source="sender.id")
    @Mapping(target="receiverId", source="receiver.id")
    @Mapping(target="senderName", source="sender.nom")
    @Mapping(target="senderFirstName", source="sender.prenom")
    @Mapping(target="time", source="timeStamp")
    MessageDto ToDto(Message message);

    @InheritInverseConfiguration
    @Mapping(target = "sender", ignore = true)
    @Mapping(target = "receiver", ignore = true)
    Message FromDto(MessageDto messageDto);

    List<MessageDto> toDtoList(List<Message> messages);
    List<Message> fromDtoList(List<MessageDto> dtos);
}
