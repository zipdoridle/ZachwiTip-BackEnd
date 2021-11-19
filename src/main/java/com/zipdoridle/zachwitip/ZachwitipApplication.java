package com.zipdoridle.zachwitip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ZachwitipApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZachwitipApplication.class, args);
    }

}
