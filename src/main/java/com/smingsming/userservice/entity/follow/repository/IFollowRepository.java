package com.smingsming.userservice.entity.follow.repository;

import com.smingsming.userservice.entity.follow.entity.FollowEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.StringTokenizer;

public interface IFollowRepository extends JpaRepository<FollowEntity, Long> {

//    Optional<FollowEntity> findByUserId(Long userId);
//    FollowEntity findByUserIdAndFollowingUserId(Long userId, Long followingUserId);
    FollowEntity findByFollowingIdAndFollowerId(String followerId, String followingId);
    List<FollowEntity> findAllByFollowingId(String uuid, Pageable pr);
    List<FollowEntity> findAllByFollowerId(String uuid, Pageable pr);
    String countByFollowingId(String followingId);    // 팔로워 수 (follower)
    String countByFollowerId(String followerId);  // 팔로우 수 (following)
    Boolean existsByFollowingIdAndFollowerId(String followingId, String followerId);
}
