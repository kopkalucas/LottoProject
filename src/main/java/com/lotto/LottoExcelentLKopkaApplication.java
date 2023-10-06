package com.lotto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LottoExcelentLKopkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LottoExcelentLKopkaApplication.class, args);
    }

}
