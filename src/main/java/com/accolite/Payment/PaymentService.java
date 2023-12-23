package com.accolite.Payment;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PaymentService {
    List<PaymentEntity> getAllPayments();
    ResponseEntity<String > savePayment(PaymentEntity payment);


    ResponseEntity<String> approvePayment(Long id, Byte option);
}
