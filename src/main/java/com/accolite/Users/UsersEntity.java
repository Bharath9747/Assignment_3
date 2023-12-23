package com.accolite.Users;

import com.accolite.Role.RoleEntity;
import com.accolite.Wallet.WalletEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")

public class UsersEntity {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_name")
    private String name;
    @Column(name = "secretCode", nullable = false)
    private String secretCode;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
    @Column(name = "user_status" )
    private Byte status=0;
    @Column(name = "user_long")
    private Double longitude;
    @Column(name = "user_lat")
    private Double lattitude;
    @Transient
    private Long roleId;
}
