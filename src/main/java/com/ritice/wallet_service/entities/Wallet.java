package com.ritice.wallet_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Wallet {
    @Id
    private String id;
    private Double balabce;
    private Long createdAt;
    private String userId;

    @ManyToOne
    private Currency currency;

    @OneToMany(mappedBy ="wallet")
    private List<WalletTransaction> walletTransactions;

}
