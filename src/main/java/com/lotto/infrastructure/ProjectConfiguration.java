package com.lotto.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;


@Configuration
public class ProjectConfiguration {

    @Bean
    Clock clock(){
        return Clock.systemUTC();
    }

}
