package com.accolite.Payment;

import com.accolite.Wallet.WalletEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")

public class PaymentEntity {
    @Id
    @GeneratedValue
    @Column(name = "payment_id")
    private Long id;
    private Long userId;
    @Column(name = "payment_type")
    private Byte paymentType=0;
    @Column(name = "vendor_id")
    private Long vendorId;
    private Double longitude;
    private Double lattitude;
    @Column(name = "unique_code")
    private Long uniqueCode;
    @Column()
    private Double amount= Double.valueOf(0);
//    0->pending
//    1->completed
//    2->rejected
//    3->flagged
    @Column()
    private Byte status=0;

}
