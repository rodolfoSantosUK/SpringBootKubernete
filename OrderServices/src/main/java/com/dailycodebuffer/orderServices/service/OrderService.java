package com.dailycodebuffer.orderServices.service;

import com.dailycodebuffer.orderServices.model.OrderRequest;

public interface OrderService {

    long placeOrder(OrderRequest orderRequest);

}
