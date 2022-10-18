package com.smingsming.userservice.entity.follow.repository;

import com.smingsming.userservice.entity.follow.entity.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IFollowRepository extends JpaRepository<FollowEntity, Long> {

    Optional<FollowEntity> findByUserId(Long userId);
    FollowEntity findByUserIdAndFollowingUserId(Long userId, Long followingUserId);
    List<FollowEntity> findAllByUserId(Long userId);
    List<FollowEntity> findAllByFollowingUserId(Long userId);
}
