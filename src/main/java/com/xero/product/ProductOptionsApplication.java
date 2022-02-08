package com.xero.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ProductOptionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductOptionsApplication.class, args);
	}

}
