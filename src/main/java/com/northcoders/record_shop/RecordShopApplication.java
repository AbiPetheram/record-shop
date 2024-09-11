package com.northcoders.record_shop;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableCaching
@SpringBootApplication
public class RecordShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordShopApplication.class, args);
	}

	@Bean
	public OpenAPI recordShopApiInfo() {
		return new OpenAPI()
				.info(new Info().title("Record Shop")
						.description("Welcome to the record shop, where all your record needs are met!")
						.version("v1")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

}
