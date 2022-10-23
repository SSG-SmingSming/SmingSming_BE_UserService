package com.smingsming.userservice.entity.follow.service;

import com.smingsming.userservice.entity.follow.client.UserServerClient;
import com.smingsming.userservice.entity.follow.entity.FollowEntity;
import com.smingsming.userservice.entity.follow.repository.IFollowRepository;
import com.smingsming.userservice.entity.follow.vo.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FollowServiceImpl implements IFollowService {

    private final IFollowRepository iFollowRepository;
    private final UserServerClient userServerClient;

    // 팔로우
    @Override
    public String followUser(Long userId, Long followingUserId) {

        UserVo following = userServerClient.getUser(userId);
        UserVo follower = userServerClient.getUser(followingUserId);

        // 자기 자신은 팔로우 할 수 없음
        if(userId == followingUserId) {
            log.info("자기 자신을 팔로우 할 수 없습니다.");
            return "본인은 팔로우할 수 없습니다.";
        }

        // user 유효성 확인
        if(following == null && follower == null)
            return "존재하지 않는 사용자입니다.";

        // 팔로우 버튼 한 번 누르면 팔로잉, 두 번 누르면 언팔로잉
        FollowEntity follow = iFollowRepository.findByUserIdAndFollowingUserId(userId, followingUserId);
        if(follow == null) {
            FollowEntity addFollow = FollowEntity.builder()
                    .userId(userId)
                    .followingUserId(followingUserId)
                    .build();
            iFollowRepository.save(addFollow);
            return "팔로우 성공";
        } else {
            iFollowRepository.delete(follow);
            return "팔로우 취소";
        }



    }

    // 언팔로우
    @Override
    public boolean unfollowUser(Long id) {

        Optional<FollowEntity> follow = iFollowRepository.findById(id);

        if(follow.isPresent()) {
            iFollowRepository.deleteById(id);
            return true;
        }

        return false;
    }

    // 팔로잉한 유저 목록 조회
    @Override
    public List<FollowEntity> getFollowingList(Long userId) {
        return iFollowRepository.findAllByUserId(userId);
    }

    // 해당 유저의 팔로워 유저 목록 조회
    @Override
    public List<FollowEntity> getFollwerList(Long userId) {
        return iFollowRepository.findAllByFollowingUserId(userId);

    }
}
