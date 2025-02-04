package fr.morgan.initiativeasso.repository;

import fr.morgan.initiativeasso.model.Like;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findLikeByUserIdAndLikedUserId(Long userId, Long likedUserId);
}
