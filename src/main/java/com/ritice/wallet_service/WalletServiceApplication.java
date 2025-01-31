package com.ritice.wallet_service;

import com.ritice.wallet_service.services.WalletService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WalletServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(WalletServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(WalletService walletService){
		return args->{
			walletService.loadData();

		};
	}

}
