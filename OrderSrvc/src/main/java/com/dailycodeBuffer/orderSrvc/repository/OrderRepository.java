package com.dailycodeBuffer.orderSrvc.repository;

import com.dailycodeBuffer.orderSrvc.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {



}
