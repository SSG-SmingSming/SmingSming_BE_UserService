package com.smingsming.userservice.entity.ticket.vo;

import lombok.Data;

@Data
public class TicketAddReqVo {

    private String name;
    private String description;
    private int price;
}
