package com.smingsming.userservice.entity.pay.vo;

import com.smingsming.userservice.entity.ticket.entity.TicketEntity;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class PayResVo {

    private Long id;
    private Timestamp payDate;
//    private int paymentType;

}
