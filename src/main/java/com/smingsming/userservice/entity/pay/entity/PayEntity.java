package com.smingsming.userservice.entity.pay.entity;

import com.smingsming.userservice.entity.ticket.entity.TicketEntity;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "pay")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    @NotNull
    @CreationTimestamp
    private Timestamp payDate;      // 결제 일자

    private String cardNumber;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private TicketEntity ticketEntity;
//


}