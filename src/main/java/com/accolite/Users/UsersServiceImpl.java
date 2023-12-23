package com.accolite.Users;

import com.accolite.Payment.PaymentService;
import com.accolite.Role.RoleEntity;
import com.accolite.Role.RoleService;
import com.accolite.Wallet.WalletEntity;
import com.accolite.Wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleService roleService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private PaymentService paymentService;

    @Override
    public List<UsersEntity> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public ResponseEntity<String> saveUser(UsersEntity user) {
        if (user.getRoleId() != null) {
            RoleEntity roleEntity = roleService.getRole(user.getRoleId());
            if (roleEntity != null) {
                user.setRole(roleEntity);
                WalletEntity wallet = new WalletEntity();
                wallet.setId(user.getId());
                if (user.getRole().getId() == 3)
                    wallet.setAmount(Double.valueOf(1000));
                else
                    wallet.setStatus((byte) 1);
                if (user.getRole().getId() == 1)
                    user.setStatus((byte) 1);
                walletService.saveWallet(wallet, user.getRole().getId());
                usersRepository.save(user);
                return new ResponseEntity<>("User Created", HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>("Field is Missing", HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public ResponseEntity<String> approveUser(Long id) {
        UsersEntity usersEntity = usersRepository.findById(id).orElse(null);
        if (usersEntity != null) {
            if (!(usersEntity.getRole().getId() == 1)) {
                usersEntity.setStatus((byte) 1);
                usersRepository.save(usersEntity);
                return new ResponseEntity<>("Approved " + usersEntity.getName(),HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("Admin cannot approve himself",HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> activateWallet(Long id) {
        UsersEntity usersEntity = usersRepository.findById(id).orElse(null);
        if (usersEntity != null) {
            if(usersEntity.getStatus()!=0) {
                if (usersEntity.getRole().getId() == 3) {
                    return walletService.setWalletStatus(id);
                } else
                    return new ResponseEntity<>("Already Activated for Vendors and Admin", HttpStatus.NOT_MODIFIED);
            }
            else
                return new ResponseEntity<>("Activate the Account first",HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> approvePayment(Long id, Byte option) {
        return paymentService.approvePayment(id, option);
    }

    @Override
    public UsersEntity getUser(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public UsersEntity findAdmin() {
        return usersRepository.findAll().stream().filter(x->x.getRole().getId()==1).collect(Collectors.toList()).stream().findFirst().get();
    }

}
