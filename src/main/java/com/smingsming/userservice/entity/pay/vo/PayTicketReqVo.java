package com.smingsming.userservice.entity.pay.vo;

import lombok.Data;

// 구매할 때
@Data
public class PayTicketReqVo {

    private Long ticketId;
    private int paymentType;
    private String bank;
    private String accountNumber;
    private String cardNumber;
}
