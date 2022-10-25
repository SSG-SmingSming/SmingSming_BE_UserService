package com.smingsming.userservice.entity.follow.repository;

import com.smingsming.userservice.entity.follow.entity.FollowEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFollowRepository extends JpaRepository<FollowEntity, Long> {

//    Optional<FollowEntity> findByUserId(Long userId);
//    FollowEntity findByUserIdAndFollowingUserId(Long userId, Long followingUserId);
    FollowEntity findByToUserIdAndFromUserId(Long userId, Long followingUserId);
    List<FollowEntity> findAllByToUserId(Long userId, Pageable pr);
    List<FollowEntity> findAllByFromUserId(Long userId, Pageable pr);
    Long countByToUserId(Long toUserId);    // 팔로워 수 (follower)
    Long countByFromUserId(Long fromUserId);  // 팔로우 수 (following)
    Boolean existsByToUserIdAndFromUserId(Long toUserId, Long fromUserId);
}
