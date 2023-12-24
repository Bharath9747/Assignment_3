package com.accolite.Users;

import com.accolite.Payment.PaymentEntity;
import com.accolite.Payment.PaymentService;
import com.accolite.Wallet.WalletEntity;
import com.accolite.Wallet.WalletService;
import com.accolite.auth.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")

public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private WalletService walletService;
    private PaymentService paymentService;

    @GetMapping("/allusers")
    public List<UsersEntity> getAllUser(){
        return usersService.getAllUsers();
    }
    @GetMapping("/allwallet")
    public List<WalletEntity> getAllWallet(){
        return walletService.getAllWalletDetails();
    }
    @GetMapping("/allpayment")
    public List<PaymentEntity> getAllPayments(){
        return paymentService.getAllPayments();
    }



    @GetMapping("/approve/account/{id}")
    public ResponseEntity<String> approveUser(@PathVariable Long id){
        return usersService.approveUser(id);
    }
    @GetMapping("/activate/{id}")
    public ResponseEntity<String> activateWallet(@PathVariable Long id){
        return usersService.activateWallet(id);
    }
    @GetMapping("/approve/payment/{id}/{option}")
    public ResponseEntity<String> approvePayment(@PathVariable Long id, @PathVariable Byte option){
        return usersService.approvePayment(id,option);
    }

}
