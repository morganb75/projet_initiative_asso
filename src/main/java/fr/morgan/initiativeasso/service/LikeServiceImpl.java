package fr.morgan.initiativeasso.service;

import fr.morgan.initiativeasso.model.Like;
import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.exception.LikeNotFoundException;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.repository.LikeRepository;
import fr.morgan.initiativeasso.repository.UserRepository;
import fr.morgan.initiativeasso.service.interfaces.LikeService;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    public LikeServiceImpl(UserRepository userRepository, LikeRepository likeRepository) {
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }

    @Override
    public void likeUser(Long userId, Long likedUserId) throws UserNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(() -> UserNotFoundException.builder()
                .message("User not found").build());

        User likedUser = userRepository.findById(likedUserId).orElseThrow(() -> UserNotFoundException.builder()
                .message("User not found").build());

        likeRepository.save(
                Like.builder()
                        .dateCreation(LocalDateTime.now())
                        .user(user)
                        .likedUser(likedUser)
                        .build()
        );
    }

    @Override
    public void unlikeUser(Long userId, Long likedUserId) throws UserNotFoundException, LikeNotFoundException {
        Like like = likeRepository.findLikeByUserIdAndLikedUserId(userId,likedUserId)
                .orElseThrow(() -> LikeNotFoundException.builder().message("Pas de like sur ce user").build());

        likeRepository.delete(like);
    }

}
