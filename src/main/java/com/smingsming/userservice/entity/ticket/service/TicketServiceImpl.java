package com.smingsming.userservice.entity.ticket.service;

import com.smingsming.userservice.entity.ticket.entity.TicketEntity;
import com.smingsming.userservice.entity.ticket.repository.ITicketRepository;
import com.smingsming.userservice.entity.ticket.vo.TicketAddReqVo;
import com.smingsming.userservice.exception.CustomException;
import com.smingsming.userservice.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements ITicketService{

    private final ITicketRepository iTicketRepository;

    // 이용권 생성
    @Override
    public TicketEntity addTicket(TicketAddReqVo ticketAddReqVo) {

        ModelMapper mapper = new ModelMapper();

        TicketEntity mapTicketEntity = mapper.map(ticketAddReqVo, TicketEntity.class);
        TicketEntity ticketEntity = iTicketRepository.save(mapTicketEntity);
        return mapTicketEntity;
    }

    // 제공 중인 이용권 목록 조회
    @Override
    public List<TicketEntity> getTicketList() {

        List<TicketEntity> ticketEntity = iTicketRepository.findAll();

        if(! ticketEntity.isEmpty()) {
            return ticketEntity;
        } else {
            throw new CustomException(ErrorCode.READ_TICKET_FAILED);
        }
    }

    // 이용권 삭제 -> 해당 이용권 서비스 종료
    @Override
    public boolean deleteTicket(Long id) {
        Optional<TicketEntity> ticketEntity = iTicketRepository.findById(id);

        if(ticketEntity.isPresent()) {
            iTicketRepository.deleteById(id);
            return true;
        } else {
            throw new CustomException(ErrorCode.TICKET_NOT_EXISTED);
        }
    }
}
