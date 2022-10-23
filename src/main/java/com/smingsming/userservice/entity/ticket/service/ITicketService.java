package com.smingsming.userservice.entity.ticket.service;

import com.smingsming.userservice.entity.ticket.entity.TicketEntity;
import com.smingsming.userservice.entity.ticket.vo.TicketAddReqVo;

import java.util.List;

public interface ITicketService {

    TicketEntity addTicket(TicketAddReqVo ticketAddReqVo);
    List<TicketEntity> getTicketList();
    boolean deleteTicket(Long id);


}
