package com.dailycodeBuffer.orderSrvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderSrvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderSrvcApplication.class, args);
	}

}
