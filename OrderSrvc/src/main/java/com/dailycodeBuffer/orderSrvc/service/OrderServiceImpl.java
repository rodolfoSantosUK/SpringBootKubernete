package com.dailycodeBuffer.orderSrvc.service;

import com.dailycodeBuffer.orderSrvc.entity.Order;
import com.dailycodeBuffer.orderSrvc.external.client.PaymentService;
import com.dailycodeBuffer.orderSrvc.external.client.ProductService;
import com.dailycodeBuffer.orderSrvc.external.request.PaymentRequest;
import com.dailycodeBuffer.orderSrvc.model.OrderRequest;
import com.dailycodeBuffer.orderSrvc.model.OrderResponse;
import com.dailycodeBuffer.orderSrvc.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final PaymentService paymentService;

    private final ProductService productService;

    @Override
    public long placeOrder(OrderRequest orderRequest) {

        productService.reduceQuantity(orderRequest.getProductId(),
                orderRequest.getQuantity());

        Order order = Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();

        order = orderRepository.save(order);

        log.info("Calling Payment Service to complete the payment");

        PaymentRequest paymentRequest
                = PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();


        String orderStatus = null;
        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment done Successfully. Changing the Oder status to PLACED");
            orderStatus = "PLACED";
        } catch (Exception e) {
            log.error("Error occurred in payment. Changing order status to PAYMENT_FAILED");
            orderStatus = "PAYMENT_FAILED";
        }

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);

        log.info("Order Places successfully with Order Id: {}", order.getId());

        return order.getId();

    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        return null;
    }
}
