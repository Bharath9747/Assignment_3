package com.accolite.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository ;

    @Override
    public List<RoleEntity> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public ResponseEntity<String> saveRole(RoleEntity role) {
        roleRepository.save(role);
        return new ResponseEntity<>("Role Added", HttpStatus.CREATED);
    }

    @Override
    public RoleEntity getRole(Long roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }
}
