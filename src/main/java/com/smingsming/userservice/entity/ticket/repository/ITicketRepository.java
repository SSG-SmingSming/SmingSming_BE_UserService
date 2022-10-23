package com.smingsming.userservice.entity.ticket.repository;

import com.smingsming.userservice.entity.ticket.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITicketRepository extends JpaRepository<TicketEntity, Long> {
}
