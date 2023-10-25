package com.lotto.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Configuration
@Profile("integration")
public class IntegrationConfiguration {

    @Bean
    @Primary
    public AdjustableClock clock(){
        return new AdjustableClock(LocalDateTime.of(2022, 11, 19, 11, 00, 0).toInstant(ZoneOffset.UTC), ZoneId.of("Europe/London"));
    }
}
