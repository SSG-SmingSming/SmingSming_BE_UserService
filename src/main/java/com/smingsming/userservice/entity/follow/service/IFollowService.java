package com.smingsming.userservice.entity.follow.service;

import com.smingsming.userservice.entity.follow.entity.FollowEntity;
import com.smingsming.userservice.entity.follow.vo.FollowCountVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IFollowService {

    String followUser(HttpServletRequest request, String followingUserId);
    List<FollowEntity> getFollowerList(String uuid, int page);
    List<FollowEntity> getFollowingList(String uuid, int page);
    FollowCountVo countFollow(String uuid);
    boolean isFollow(String followingId, HttpServletRequest request);
}
