package com.accolite.Wallet;


import com.accolite.Users.UsersEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wallet")
public class WalletEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column()
    private Double amount= Double.valueOf(0);
    @Column(name = "wallet_status")
    private Byte status=0;
    @Column(name = "payment_type")
    private Byte paymentType=0;
    @ElementCollection
    @CollectionTable(name = "unique_codes", joinColumns = @JoinColumn(name = "wallet_id"))
    @Column(name = "code")
    private List<Long> uniqueCodes;
}
