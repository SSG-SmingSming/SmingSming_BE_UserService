package com.smingsming.userservice.entity.pay.vo;

import lombok.Data;

// 결제내역
@Data
public class PayHistoryResVo {

    private Long id;
    private int paymentType;
    private String Bank;
    private String accountNumber;
    private String cardNumber;

}
