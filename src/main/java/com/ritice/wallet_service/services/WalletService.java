package com.ritice.wallet_service.services;

import com.ritice.wallet_service.entities.Currency;
import com.ritice.wallet_service.entities.Wallet;
import com.ritice.wallet_service.entities.WalletTransaction;
import com.ritice.wallet_service.enums.TransactionType;
import com.ritice.wallet_service.repositories.CurrencyRepository;
import com.ritice.wallet_service.repositories.WalletRepository;
import com.ritice.wallet_service.repositories.WalletTransactionRepository;
import jakarta.transaction.Transactional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;


@Service
public class WalletService {
    private final CurrencyRepository currencyRepository;
    private final WalletTransactionRepository walletTransactionRepository;
    private  final  WalletRepository walletRepository;

    public WalletService(
            CurrencyRepository currencyRepository,
            WalletTransactionRepository walletTransactionRepository,
            WalletRepository walletRepository) {

        this.currencyRepository = currencyRepository;
        this.walletTransactionRepository = walletTransactionRepository;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public void loadData() throws IOException {
        URI uri= new ClassPathResource("currency.csv").getURI();
        Path path= Paths.get(uri);

        List<String> lines=Files.readAllLines(path);
        for (int i=1;i<lines.size();i++ ){
           String[] line=lines.get(i).split( ";");
            Currency currency= Currency.builder()
                    .code(line[0])
                    .name(line[1])
                    .salePrice(Double.parseDouble(line[2]))
                    .purchasePrice(Double.parseDouble(line[3]))
                    .build();

            currencyRepository.save(currency);

        }

        Stream.of("AFN","USD","EUR").forEach(
                currencyCode->{
           Currency currency=currencyRepository
                   .findById(currencyCode)
                   .orElseThrow(()->new RuntimeException(String.format("Curency %s not found",currencyCode)));

                    Wallet  wallet= new Wallet();
                    wallet.setBalabce(10000.0);
                    wallet.setCurrency(currency);
                    wallet.setCreatedAt(System.currentTimeMillis());
                    wallet.setUserId("user1");
                    wallet.setId(UUID.randomUUID().toString());
                    walletRepository.save(wallet);

                });

        walletRepository.findAll().forEach(wallet -> {
            for (int i=0;i<10;i++){
                WalletTransaction debitWalletTransaction =WalletTransaction.builder()
                        .amount(Math.random()*1000)
                        .wallet(wallet)
                        .type(TransactionType.DEBIT)
                        .timestamps(System.currentTimeMillis())
                    .build();
                walletTransactionRepository.save(debitWalletTransaction);
                wallet.setBalabce(wallet.getBalabce()-debitWalletTransaction.getAmount());
                walletRepository.save(wallet);


                WalletTransaction creditWalletTransaction =WalletTransaction.builder()
                        .amount(Math.random()*1000)
                        .wallet(wallet)
                        .type(TransactionType.CREDIT)
                        .timestamps(System.currentTimeMillis())
                        .build();
                walletTransactionRepository.save(creditWalletTransaction);
                wallet.setBalabce(wallet.getBalabce()+creditWalletTransaction.getAmount());
                walletRepository.save(wallet);

            }

        });
    }
}
