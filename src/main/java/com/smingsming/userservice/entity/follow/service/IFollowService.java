package com.smingsming.userservice.entity.follow.service;

import com.smingsming.userservice.entity.follow.entity.FollowEntity;
import com.smingsming.userservice.entity.follow.vo.FollowCountVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IFollowService {

    String followUser(HttpServletRequest request, Long followingUserId);
    boolean unfollowUser(Long id);
    List<FollowEntity> getFollowerList(Long userId, int page);
    List<FollowEntity> getFollowingList(Long userId, int page);
    FollowCountVo countFollow(Long userId);
    boolean isFollow(Long toUserId, HttpServletRequest request);
}
