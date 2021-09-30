package com.nanmu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NanmuApplication {

    public static void main(String[] args) {
        SpringApplication.run(NanmuApplication.class, args);
    }

}
