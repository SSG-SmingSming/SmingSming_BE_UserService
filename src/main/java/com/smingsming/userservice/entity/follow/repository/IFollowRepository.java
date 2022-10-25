package com.smingsming.userservice.entity.follow.repository;

import com.smingsming.userservice.entity.follow.entity.FollowEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFollowRepository extends JpaRepository<FollowEntity, Long> {

//    Optional<FollowEntity> findByUserId(Long userId);
//    FollowEntity findByUserIdAndFollowingUserId(Long userId, Long followingUserId);
    FollowEntity findByFollowingIdAndFollowerId(Long userId, Long followingId);
    List<FollowEntity> findAllByFollowingId(Long userId, Pageable pr);
    List<FollowEntity> findAllByFollowerId(Long userId, Pageable pr);
    Long countByFollowingId(Long followingId);    // 팔로워 수 (follower)
    Long countByFollowerId(Long followerId);  // 팔로우 수 (following)
    Boolean existsByFollowingIdAndFollowerId(Long followingId, Long followerId);
}
