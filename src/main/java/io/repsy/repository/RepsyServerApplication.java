package io.repsy.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "io.repsy")
public class RepsyServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RepsyServerApplication.class, args);
    }
}


