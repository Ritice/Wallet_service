package com.ritice.wallet_service.repositories;

import com.ritice.wallet_service.entities.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction,Long > {
}
