package com.lotto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LottoAplication {

    public static void main(String[] args) {
        SpringApplication.run(LottoAplication.class, args);
    }

}
