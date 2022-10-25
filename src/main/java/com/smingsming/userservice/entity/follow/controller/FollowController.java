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
    @PostMapping(value ="/follow/{toUserId}")
    public ResponseEntity<?> followUser(HttpServletRequest request, @PathVariable(value = "toUserId") Long toUserId) {
        System.out.println(request);
        System.out.println(toUserId);

        String follow = iFollowService.followUser(request, toUserId);

        return ResponseEntity.status(HttpStatus.OK).body(follow);

    }

    // 언팔로우
    @DeleteMapping(value ="/unfollow/{id}")
    public ResponseEntity<?> unfollowUser(@PathVariable(value = "id") Long id) {

        boolean unfollow = iFollowService.unfollowUser(id);

        if(unfollow)
            return ResponseEntity.status(HttpStatus.OK).body("언팔로잉 성공");
        else
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("언팔로잉 실패");
    }

    // 해당 유저의 팔로워 유저 목록 조회
    @GetMapping("/follower/{userId}")
    public List<FollowEntity> getFollowerList(@PathVariable (value = "userId") Long userId,
                                               @RequestParam(name = "page", defaultValue = "1") int page) {
        return iFollowService.getFollowerList(userId, page);
    }

    // 팔로잉한 유저 목록 조회
    @GetMapping("/{userId}")
    public List<FollowEntity> getFollowingList(@PathVariable (value = "userId") Long userId,
                                              @RequestParam(name = "page", defaultValue = "1") int page) {
        return iFollowService.getFollowingList(userId, page);
    }

    // 팔로우, 팔로워 집계
    @GetMapping("/count/{userId}")
    public ResponseEntity<?> countFollow(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(iFollowService.countFollow(userId));
    }
    
    // 팔로우 여부 조회
    @GetMapping("/check/{toUserId}")
    public ResponseEntity<?> getIsFollow(@PathVariable(value = "toUserId") Long toUserId,
                                         HttpServletRequest request) {
        boolean result = iFollowService.isFollow(toUserId, request);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
