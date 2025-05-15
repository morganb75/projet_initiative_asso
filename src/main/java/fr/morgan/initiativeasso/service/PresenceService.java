package fr.morgan.initiativeasso.service;

import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.dto.UserWsOnLineDto;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.repository.UserRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class PresenceService {
    private final Map<String, UserWsOnLineDto> connectedUsers = new ConcurrentHashMap<>();
    private final UserRepository userRepository;

    public PresenceService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void userConnected(String sessionId,String userName) throws UserNotFoundException {
        User user = userRepository.findUserByEmail(userName).orElseThrow(() -> new UserNotFoundException(userName));

        UserWsOnLineDto connectedUserDto = UserWsOnLineDto.builder()
                .id(user.getId())
                .userName(user.getEmail())
                .build();

        connectedUsers.put(sessionId, connectedUserDto);
    }

    public void userDisconnected(String sessionId) {
        connectedUsers.remove(sessionId);
    }

    public Collection<UserWsOnLineDto> getConnectedUsers() {
        return connectedUsers.values();

    }
}
