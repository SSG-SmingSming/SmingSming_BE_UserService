package com.smingsming.userservice.entity.follow.client;

import com.smingsming.userservice.entity.follow.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-server")
public interface UserServerClient {

    // user 서버에서 userId 가져오기
    @GetMapping("/user/get/{uuid}")
    UserVo getUser(@PathVariable String uuid);

}
