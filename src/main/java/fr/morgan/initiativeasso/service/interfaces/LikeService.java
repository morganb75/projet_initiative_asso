package fr.morgan.initiativeasso.service.interfaces;

import fr.morgan.initiativeasso.model.exception.LikeNotFoundException;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;

import org.springframework.stereotype.Service;

public interface LikeService {
    void likeUser(Long userId, Long likedUserId) throws UserNotFoundException;
    void unlikeUser(Long userId, Long likedUserId) throws UserNotFoundException, LikeNotFoundException;
}
