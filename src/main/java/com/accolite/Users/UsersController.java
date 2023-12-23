package com.accolite.Users;

import com.accolite.Role.RoleEntity;
import com.accolite.Role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/all")
    public List<UsersEntity> getAllUser(){
        return usersService.getAllUsers();
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody UsersEntity user) {
        return usersService.saveUser(user);

    }
    @GetMapping("/admin/approve/account/{id}")
    public ResponseEntity<String> approveUser(@PathVariable Long id){
        return usersService.approveUser(id);
    }
    @GetMapping("/admin/activate/{id}")
    public ResponseEntity<String> activateWallet(@PathVariable Long id){
        return usersService.activateWallet(id);
    }
    @GetMapping("/admin/approve/payment/{id}/{option}")
    public ResponseEntity<String> approvePayment(@PathVariable Long id, @PathVariable Byte option){
        return usersService.approvePayment(id,option);
    }

}
