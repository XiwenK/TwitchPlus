package com.laioffer.twich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TwichApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwichApplication.class, args);
    }

}
