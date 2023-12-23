package com.accolite.Role;


import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {
    List<RoleEntity> getAllRole();
    ResponseEntity<String> saveRole(RoleEntity role);

    RoleEntity getRole(Long roleId);
}
