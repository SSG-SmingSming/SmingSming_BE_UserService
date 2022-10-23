package com.smingsming.userservice.entity.follow.service;

import com.smingsming.userservice.entity.follow.entity.FollowEntity;

import java.util.List;

public interface IFollowService {

    String followUser(Long userId, Long followingUserId);
    boolean unfollowUser(Long id);
    List<FollowEntity> getFollowingList(Long userId);
    List<FollowEntity> getFollwerList(Long userId);

}
