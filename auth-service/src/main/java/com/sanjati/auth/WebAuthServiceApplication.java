package com.sanjati.auth;

import com.sanjati.api.MyApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class WebAuthServiceApplication implements MyApp {
	public static void main(String[] args) {
		SpringApplication.run(WebAuthServiceApplication.class, args);
	}

	@Override
	public void runApp(String[] args) {
		SpringApplication.run(WebAuthServiceApplication.class, args);
	}
}
