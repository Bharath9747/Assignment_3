package com.accolite.Users;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsersService {
    List<UsersEntity> getAllUsers();
    ResponseEntity<String> saveUser(UsersEntity user);
    ResponseEntity<String> approveUser(Long id);

    ResponseEntity<String> activateWallet(Long id);


    ResponseEntity<String> approvePayment(Long id, Byte option);

    UsersEntity getUser(Long id);

    UsersEntity findAdmin();
}
