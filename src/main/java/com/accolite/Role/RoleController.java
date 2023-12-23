package com.accolite.Role;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/all")
    public List<RoleEntity> getAllRole(){
        return roleService.getAllRole();
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveRole(@RequestBody RoleEntity role) {
        return roleService.saveRole(role);
    }
}
