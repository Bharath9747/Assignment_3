package com.accolite.Users;

import com.accolite.auth.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsersService {
    List<UsersEntity> getAllUsers();
    ResponseEntity<String> approveUser(Long id);

    ResponseEntity<String> activateWallet(Long id);


    ResponseEntity<String> approvePayment(Long id, Byte option);

}
