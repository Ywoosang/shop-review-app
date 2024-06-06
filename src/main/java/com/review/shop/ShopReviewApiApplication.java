package com.review.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ShopReviewApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopReviewApiApplication.class, args);
	}
}
