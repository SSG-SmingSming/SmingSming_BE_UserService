package com.smingsming.userservice.entity.pay.controller;

import com.smingsming.userservice.entity.pay.service.IPayService;
import com.smingsming.userservice.entity.pay.vo.PayHistoryResVo;
import com.smingsming.userservice.entity.pay.vo.PayResVo;
import com.smingsming.userservice.entity.pay.vo.PayTicketReqVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pay")
@RequiredArgsConstructor
public class PayController {

    private final IPayService iPayService;

    // 이용권 구매
    @PostMapping("/buy")
    public ResponseEntity<?> buyTicket(@RequestBody PayTicketReqVo payTicketReqVo) {
        boolean result = iPayService.buyTicket(payTicketReqVo);
        if(result == true)
            return ResponseEntity.status(HttpStatus.OK).body(result);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("결제가 실패하였습니다. 다시 시도해주세요.");
    }

    // 구매한 이용권 조회
    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getTicketByUser(@PathVariable(value = "userId") Long userId) {
        List<PayResVo> payResVo = iPayService.getTicketByUser(userId);

        if(payResVo !=null)
            return ResponseEntity.status(HttpStatus.OK).body(payResVo);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("구매한 이용권이 존재하지 않습니다.");

    }

//    // 결제 내역 조회
//    @GetMapping("/get/history/{userId}")
//    public ResponseEntity<?> getPayHistory(@PathVariable(value = "userId") Long userId) {
//        List<PayHistoryResVo> resVoList = iPayService.getPayHistory(userId);
//
//        if(resVoList !=null)
//            return ResponseEntity.status(HttpStatus.OK).body(resVoList);
//        else
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("구매 내역이 존재하지 않습니다.");
//    }
//
}
