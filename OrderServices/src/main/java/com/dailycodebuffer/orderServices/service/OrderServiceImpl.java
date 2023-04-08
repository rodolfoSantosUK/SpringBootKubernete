package com.dailycodebuffer.orderServices.service;

import com.dailycodebuffer.orderServices.entity.Order;
import com.dailycodebuffer.orderServices.model.OrderRequest;
import com.dailycodebuffer.orderServices.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    @Override
    public long placeOrder(OrderRequest orderRequest) {

       Order order =  Order.builder()
                      .amount(orderRequest.getTotalAmount())
                      .orderStatus("CREATED")
                      .productId(orderRequest.getProductId())
                      .orderDate(Instant.now())
                      .quantity(orderRequest.getQuantity())
                      .build();

       order = orderRepository.save(order);

         return order.getId();

     }
}
