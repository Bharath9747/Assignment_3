package com.accolite.Users;

import com.accolite.Payment.PaymentService;
import com.accolite.Wallet.WalletEntity;
import com.accolite.Wallet.WalletService;
import com.accolite.auth.AuthenticationResponse;
import com.accolite.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private WalletService walletService;
    @Autowired
    private PaymentService paymentService;


    @Override
    public List<UsersEntity> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public ResponseEntity<String> approveUser(Long id) {
        UsersEntity usersEntity = usersRepository.findById(id).orElse(null);
        if (usersEntity != null) {
            if (!(usersEntity.getRole().ordinal() == 0)) {
                usersEntity.setStatus((byte) 1);
                usersRepository.save(usersEntity);
                return new ResponseEntity<>("Approved " + usersEntity.getName(), HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("Admin cannot approve himself", HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> activateWallet(Long id) {
        UsersEntity usersEntity = usersRepository.findById(id).orElse(null);
        if (usersEntity != null) {
            if (usersEntity.getStatus() != 0) {
                if (usersEntity.getRole().ordinal() == 2) {
                    return walletService.setWalletStatus(id);
                } else
                    return new ResponseEntity<>("Already Activated for Vendors and Admin", HttpStatus.NOT_MODIFIED);
            } else
                return new ResponseEntity<>("Activate the Account first", HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> approvePayment(Long id, Byte option) {
        return paymentService.approvePayment(id, option);
    }





}
