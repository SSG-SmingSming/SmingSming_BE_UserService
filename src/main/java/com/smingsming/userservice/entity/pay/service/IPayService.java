package com.smingsming.userservice.entity.pay.service;

import com.smingsming.userservice.entity.pay.vo.PayHistoryResVo;
import com.smingsming.userservice.entity.pay.vo.PayTicketReqVo;
import com.smingsming.userservice.entity.pay.vo.PayResVo;

import java.util.List;

public interface IPayService {

    boolean buyTicket(PayTicketReqVo payTicketReqVo);

    List<PayResVo> getTicketByUser(Long userId);

//    List<PayHistoryResVo> getPayHistory(Long userId);

}
