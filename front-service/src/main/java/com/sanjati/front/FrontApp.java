package com.sanjati.front;

import com.sanjati.api.MyApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class FrontApp implements MyApp {
    public static void main(String[] args) {
        SpringApplication.run(FrontApp.class, args);
    }
    @Override
    public void runApp(String[] args) {
        SpringApplication.run(FrontApp.class, args);
    }
}