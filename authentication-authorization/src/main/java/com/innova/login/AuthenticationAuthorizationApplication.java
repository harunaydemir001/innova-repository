package com.innova.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.innova.login.controllers.AuthController.java"})
public class AuthenticationAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationAuthorizationApplication.class, args);
	}

}
