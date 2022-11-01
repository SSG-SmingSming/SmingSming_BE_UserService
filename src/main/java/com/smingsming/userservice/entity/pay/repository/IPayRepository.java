package com.smingsming.userservice.entity.pay.repository;

import com.smingsming.userservice.entity.pay.entity.PayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPayRepository extends JpaRepository<PayEntity, Long> {

    List<PayEntity> findAllByUuid(String uuid);
}
