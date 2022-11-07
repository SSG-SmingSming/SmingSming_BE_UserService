package com.smingsming.userservice.entity.pay.service;

import com.smingsming.userservice.entity.pay.entity.PayEntity;
import com.smingsming.userservice.entity.pay.repository.IPayRepository;
import com.smingsming.userservice.entity.pay.vo.PayTicketReqVo;
import com.smingsming.userservice.entity.pay.vo.PayResVo;
import com.smingsming.userservice.exception.CustomException;
import com.smingsming.userservice.exception.ErrorCode;
import com.smingsming.userservice.global.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PayServiceImpl implements IPayService{

    private final IPayRepository iPayRepository;
    private final JwtTokenProvider jwtTokenProvider;

    // 이용권 구매
    @Override
    @Transactional
    public PayEntity buyTicket(PayTicketReqVo payTicketReqVo, HttpServletRequest request) {

        String uuid = jwtTokenProvider.getUuid(jwtTokenProvider.resolveToken(request));
        ModelMapper mapper = new ModelMapper();

        PayEntity mapPay = mapper.map(payTicketReqVo, PayEntity.class);

        if(mapPay.getCardNumber().isEmpty())
            throw new CustomException(ErrorCode.TICKET_BUY_FAILED);

        mapPay.setUuid(uuid);
        PayEntity payResVo = iPayRepository.save(mapPay);

        return payResVo;
    }

    // 구매한 이용권 및 결제 내역 조회
    @Override
    public List<PayResVo> getTicketByUser(String uuid) {

        List<PayEntity> payEntity = iPayRepository.findAllByUuid(uuid);

        if(! payEntity.isEmpty()) {
            List<PayResVo> payResVoList = new ArrayList<>();
            ModelMapper mapper = new ModelMapper();

            payEntity.forEach(v -> {
                payResVoList.add(mapper.map(v, PayResVo.class));
            });

            return payResVoList;
        } else {
            throw new CustomException(ErrorCode.TICKET_PAYHISTORY_FAILED);
        }
    }

}
