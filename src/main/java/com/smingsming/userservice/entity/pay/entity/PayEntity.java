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

    @NotNull
    @CreationTimestamp
    private Timestamp payDate;      // 결제 일자

    @NotNull
    private Long userId;            // 유저 번호

    @NotNull
    private int paymentType;        // 수단 유형

    private String bank;            // 은행명

    private String accountNumber;   // 계좌번호

    private String cardNumber;      // 카드번호

    @ManyToOne(fetch = FetchType.LAZY)
    private TicketEntity ticketEntity;

}