package com.phat.hotcine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HotCineApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotCineApplication.class, args);
    }

}
