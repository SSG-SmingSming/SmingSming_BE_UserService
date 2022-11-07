package com.smingsming.userservice.entity.follow.client;

import com.smingsming.userservice.entity.follow.vo.UserDetailVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-server")
public interface UserServiceClient {

    // user 서버에서 userId 가져오기
    @GetMapping("/user/get/{uuid}")
    UserDetailVo getUser(@PathVariable String uuid);
}
