package com.ritice.wallet_service.repositories;

import com.ritice.wallet_service.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency,String> {
}
