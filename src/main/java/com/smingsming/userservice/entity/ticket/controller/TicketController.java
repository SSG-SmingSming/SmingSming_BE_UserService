package com.smingsming.userservice.entity.ticket.controller;

import com.smingsming.userservice.entity.ticket.entity.TicketEntity;
import com.smingsming.userservice.entity.ticket.service.ITicketService;
import com.smingsming.userservice.entity.ticket.vo.TicketAddReqVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final ITicketService iTicketService;

    // 이용권 생성
    @PostMapping("/add")
    public ResponseEntity<?> addTicket(@RequestBody TicketAddReqVo ticketAddReqVo) {

        TicketEntity ticketEntity = iTicketService.addTicket(ticketAddReqVo);
        if(ticketEntity != null)
            return ResponseEntity.status(HttpStatus.OK).body(ticketEntity);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("다시 시도해주세요.");
    }

    // 제공 중인 이용권 목록 조회
    @GetMapping("/getAll")
    public ResponseEntity<?> getTicketList() {

        List<TicketEntity> result = iTicketService.getTicketList();

        if(result != null)
            return ResponseEntity.status(HttpStatus.OK).body(result);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이용권이 존재하지 않습니다.");
    }

    // 이용권 삭제 -> 해당 이용권 서비스 종료
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable (value = "id") Long id) {

        boolean result = iTicketService.deleteTicket(id);

        if(result)
            return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 실패");
    }
}
