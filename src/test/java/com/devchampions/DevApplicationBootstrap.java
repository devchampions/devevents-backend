package com.devchampions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DevApplicationBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(DevApplicationBootstrap.class, args);
    }
}
