package com.accolite.Payment;

import com.accolite.Users.UsersEntity;
import com.accolite.Wallet.WalletEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")

public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/all")
    public List<PaymentEntity> getAllPayments(){
        return paymentService.getAllPayments();
    }
    @PostMapping("/save")
    public ResponseEntity<String> savePayment(@RequestBody PaymentEntity payment) {
        return paymentService.savePayment(payment);
    }

}
