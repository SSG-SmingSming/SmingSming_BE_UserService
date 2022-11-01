package com.smingsming.userservice.entity.follow.controller;

import com.smingsming.userservice.entity.follow.entity.FollowEntity;
import com.smingsming.userservice.entity.follow.service.IFollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/following")
@RequiredArgsConstructor
public class FollowController {

    private final IFollowService iFollowService;

    // 팔로우
    @PostMapping(value ="/follow/{followingId}")
    public ResponseEntity<?> followUser(HttpServletRequest request, @PathVariable(value = "followingId") String followingId) {

        String follow = iFollowService.followUser(request, followingId);

        return ResponseEntity.status(HttpStatus.OK).body(follow);

    }

    // 해당 유저의 팔로워 유저 목록 조회
    @GetMapping("/follower/{uuid}")
    public List<FollowEntity> getFollowerList(@PathVariable(value = "uuid") String uuid,
                                               @RequestParam(name = "page", defaultValue = "1") int page) {
        return iFollowService.getFollowerList(uuid, page);
    }

    // 팔로잉한 유저 목록 조회
    @GetMapping("/{uuid}")
    public List<FollowEntity> getFollowingList(@PathVariable(value = "uuid") String uuid,
                                              @RequestParam(name = "page", defaultValue = "1") int page) {
        return iFollowService.getFollowingList(uuid, page);
    }

    // 팔로우, 팔로워 집계
    @GetMapping("/count/{uuid}")
    public ResponseEntity<?> countFollow(@PathVariable(value = "uuid") String uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(iFollowService.countFollow(uuid));
    }
    
    // 팔로우 여부 조회
    @GetMapping("/check/{followingId}")
    public ResponseEntity<?> getIsFollow(@PathVariable(value = "followingId") String followingId,
                                         HttpServletRequest request) {
        boolean result = iFollowService.isFollow(followingId, request);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
