package com.dailycodeBuffer.Srvc.service;

import com.dailycodeBuffer.Srvc.model.PaymentRequest;
import com.dailycodeBuffer.Srvc.model.PaymentResponse;

public interface PaymentService {


    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(String orderId);

}
