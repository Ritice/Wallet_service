package com.ritice.wallet_service.entities;

import com.ritice.wallet_service.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long timestamps;
    private Double amount;

    @ManyToOne
    private Wallet  wallet;

    @Enumerated(EnumType.STRING )
    private TransactionType type;

}
