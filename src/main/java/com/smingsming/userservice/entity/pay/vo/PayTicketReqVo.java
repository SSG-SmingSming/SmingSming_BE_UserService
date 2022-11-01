package com.smingsming.userservice.entity.pay.vo;

import lombok.Data;

// 구매할 때
@Data
public class PayTicketReqVo {

    private Long ticketId;
    private String cardNumber;
}
