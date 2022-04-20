package com.sanjati.gateway;

import com.sanjati.api.MyApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApp implements MyApp {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class, args);
    }
    @Override
    public void runApp(String[] args) {

        main(null);
    }
}