package com.accolite.Wallet;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WalletService {
    List<WalletEntity> getAllWalletDetails();

    void saveWallet(WalletEntity wallet, Long id);

    ResponseEntity<String> addMoney(Long id, Double amount);

    ResponseEntity<String> setWalletStatus(Long id);

    ResponseEntity<String> changePaymentType(Long id);

    void updateWallet(Long vendorId, Long walletId, Double amount);

    void updateWallet(Long userId, Double amount);


}
