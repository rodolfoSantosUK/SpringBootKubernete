package com.dailycodeBuffer.orderSrvc.service;


import com.dailycodeBuffer.orderSrvc.model.OrderRequest;

public interface OrderService {

    long placeOrder(OrderRequest orderRequest);

}
