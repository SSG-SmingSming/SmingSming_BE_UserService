package com.smingsming.userservice.entity.pay.controller;

import com.smingsming.userservice.entity.pay.service.IPayService;
import com.smingsming.userservice.entity.pay.vo.PayHistoryResVo;
import com.smingsming.userservice.entity.pay.vo.PayResVo;
import com.smingsming.userservice.entity.pay.vo.PayTicketReqVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/pay")
@RequiredArgsConstructor
public class PayController {

    private final IPayService iPayService;

    // 이용권 구매
    @PostMapping("/buy")
    public ResponseEntity<?> buyTicket(@RequestBody PayTicketReqVo payTicketReqVo, HttpServletRequest request) {
        boolean result = iPayService.buyTicket(payTicketReqVo, request);
        if(result == true)
            return ResponseEntity.status(HttpStatus.OK).body(result);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("결제가 실패하였습니다. 다시 시도해주세요.");
    }

    // 구매한 이용권, 결제 내역 조회
    @GetMapping("/get/{uuid}")
    public ResponseEntity<?> getTicketByUser(@PathVariable(value = "uuid") String uuid) {
        List<PayResVo> payResVo = iPayService.getTicketByUser(uuid);

        if(payResVo !=null)
            return ResponseEntity.status(HttpStatus.OK).body(payResVo);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("구매한 이용권이 존재하지 않습니다.");

    }

}
