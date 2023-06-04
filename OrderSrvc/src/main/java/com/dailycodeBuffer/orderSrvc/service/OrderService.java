package com.dailycodeBuffer.orderSrvc.service;


import com.dailycodeBuffer.orderSrvc.model.OrderRequest;
import com.dailycodeBuffer.orderSrvc.model.OrderResponse;

public interface OrderService {

    long placeOrder(OrderRequest orderRequest);


    OrderResponse getOrderDetails(long orderId);

}
