package com.sanjati.core;

import com.sanjati.api.MyApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j

@SpringBootApplication
public class CoreApp implements MyApp {
    public static void main(String[] args) {
        log.debug("start");
        SpringApplication.run(CoreApp.class, args);

    }
    @Override
    public void runApp(String[] args) {
        SpringApplication.run(CoreApp.class, args);
    }
}
