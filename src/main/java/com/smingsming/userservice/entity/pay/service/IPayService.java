package com.smingsming.userservice.entity.pay.service;

import com.smingsming.userservice.entity.pay.entity.PayEntity;
import com.smingsming.userservice.entity.pay.vo.PayHistoryResVo;
import com.smingsming.userservice.entity.pay.vo.PayTicketReqVo;
import com.smingsming.userservice.entity.pay.vo.PayResVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IPayService {

    PayEntity buyTicket(PayTicketReqVo payTicketReqVo, HttpServletRequest request);
    List<PayResVo> getTicketByUser(String uuid);

}
