package com.smingsming.userservice.entity.follow.service;

import com.smingsming.userservice.entity.follow.client.UserServerClient;
import com.smingsming.userservice.entity.follow.entity.FollowEntity;
import com.smingsming.userservice.entity.follow.repository.IFollowRepository;
import com.smingsming.userservice.entity.follow.vo.FollowCountVo;
import com.smingsming.userservice.global.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FollowServiceImpl implements IFollowService {

    private final IFollowRepository iFollowRepository;
    private final UserServerClient userServerClient;
    private final JwtTokenProvider jwtTokenProvider;

    // 팔로우
    @Override
    public String followUser(HttpServletRequest request, String followingId) {

        String followerId = String.valueOf(jwtTokenProvider.getUuid(jwtTokenProvider.resolveToken(request)));

        // 자기 자신은 팔로우 할 수 없음
        if(followerId.equals(followingId)) {
            log.info("자기 자신을 팔로우 할 수 없습니다.");
            return "본인은 팔로우할 수 없습니다.";
        }

//        // user 유효성 확인
//        if(following == null && follower == null)
//            return "존재하지 않는 사용자입니다.";

        // 팔로우 버튼 한 번 누르면 팔로잉, 두 번 누르면 언팔로잉
        FollowEntity follow = iFollowRepository.findByFollowingIdAndFollowerId(followingId, followerId);
        if(follow == null) {
            FollowEntity addFollow = FollowEntity.builder()
                    .followerId(followerId)
                    .followingId(followingId)
                    .build();
            iFollowRepository.save(addFollow);
            return "팔로우 성공";
        } else {
            iFollowRepository.delete(follow);
            return "팔로우 취소";
        }

    }

    // 해당 유저의 팔로워 유저 목록 조회
    @Override
    public List<FollowEntity> getFollowerList(String uuid, int page) {
        Pageable pr = PageRequest.of(page - 1 , 20, Sort.by("id").descending());

        return iFollowRepository.findAllByFollowingId(uuid, pr);
    }


    // 팔로잉한 유저 목록 조회
    @Override
    public List<FollowEntity> getFollowingList(String uuid, int page) {
        Pageable pr = PageRequest.of(page - 1 , 20, Sort.by("id").descending());

        return iFollowRepository.findAllByFollowerId(uuid, pr);
    }

    // 팔로우, 팔로워 집계
    @Override
    public FollowCountVo countFollow(String uuid) {

        String toCount = iFollowRepository.countByFollowingId(uuid);
        String fromCount = iFollowRepository.countByFollowerId(uuid);

        FollowCountVo followCount = new FollowCountVo(toCount, fromCount);
        return followCount;
    }

    // 팔로우 여부 확인
    @Override
    public boolean isFollow(String followingId, HttpServletRequest request) {
        String followerId = String.valueOf(jwtTokenProvider.getUuid(jwtTokenProvider.resolveToken(request)));

        return iFollowRepository.existsByFollowingIdAndFollowerId(followingId, followerId);
    }
}
