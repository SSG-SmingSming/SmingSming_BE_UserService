package com.smingsming.userservice.entity.pay.service;

import com.smingsming.userservice.entity.pay.entity.PayEntity;
import com.smingsming.userservice.entity.pay.repository.IPayRepository;
import com.smingsming.userservice.entity.pay.vo.PayHistoryResVo;
import com.smingsming.userservice.entity.pay.vo.PayTicketReqVo;
import com.smingsming.userservice.entity.pay.vo.PayResVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PayServiceImpl implements IPayService{

    private final IPayRepository iPayRepository;

    // 이용권 구매
    @Override
    public boolean buyTicket(PayTicketReqVo payTicketReqVo) {

        ModelMapper mapper = new ModelMapper();

        PayEntity mapPay = mapper.map(payTicketReqVo, PayEntity.class);
        PayEntity payResVo = iPayRepository.save(mapPay);

        if(payResVo != null)
            return  true;
        else
            return false;
    }

    // 구매한 이용권 조회
    @Override
    public List<PayResVo> getTicketByUser(Long userId) {

        List<PayEntity> payEntity = iPayRepository.findAllByUserId(userId);

//        if(payEntity.size() != 0) {
//            return new ModelMapper().map(payEntity, PayResVo.class);
//        }

        if(! payEntity.isEmpty()) {
            List<PayResVo> payResVoList = new ArrayList<>();
            ModelMapper mapper = new ModelMapper();

            payEntity.forEach(v -> {
                payResVoList.add(mapper.map(v, PayResVo.class));
            });

            return payResVoList;
        }

        return null;
    }

//    // 결제 내역 조회
//    @Override
//    public List<PayHistoryResVo> getPayHistory(Long userId) {
//
//        List<PayEntity> payEntityList = iPayRepository.findAllByUserId(userId);
//
//        if(! payEntityList.isEmpty()) {
//            List<PayHistoryResVo> returnVo = new ArrayList<>();
//
//            ModelMapper mapper = new ModelMapper();
//
//            // 람다(lamda) 형식
//            payEntityList.forEach( v -> {
//                returnVo.add(mapper.map(v, PayHistoryResVo.class));
//            });
//
//            return returnVo;
//        }
//
//        return null;
//    }
}
