package com.app.transfermoney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TransferMoneyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransferMoneyApplication.class, args);
	}

}
