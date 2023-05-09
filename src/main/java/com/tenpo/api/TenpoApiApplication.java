package com.tenpo.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan
@EnableJpaRepositories
public class TenpoApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TenpoApiApplication.class, args);
    }
}
