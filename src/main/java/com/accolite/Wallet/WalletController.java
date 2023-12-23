package com.accolite.Wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")

public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping("/all")
    public List<WalletEntity> getAllUser(){
        return walletService.getAllWalletDetails();
    }

    @GetMapping("/addmoney/{id}/{amount}")
    public ResponseEntity<String> addMoney(@PathVariable Long id,@PathVariable Double amount){
        return walletService.addMoney(id,amount);
    }
    @GetMapping("/changepayment/{id}")
    public ResponseEntity<String> changePayment(@PathVariable Long id){
        return walletService.changePaymentType(id);
    }

}
