package fr.morgan.initiativeasso.service.interfaces;

import fr.morgan.initiativeasso.model.Like;
import fr.morgan.initiativeasso.model.exception.ExistingLikeException;
import fr.morgan.initiativeasso.model.exception.LikeNotFoundException;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;

import java.util.List;

import org.springframework.stereotype.Service;

public interface LikeService {
    void likeUser(Long userId, Long likedUserId) throws UserNotFoundException, ExistingLikeException;
    void unlikeUser(Long userId, Long likedUserId) throws UserNotFoundException, LikeNotFoundException, ExistingLikeException;
    List<Like> likesListByUserId(Long id);
}
