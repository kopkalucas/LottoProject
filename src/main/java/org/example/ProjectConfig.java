package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class ProjectConfig {

    @Bean
    public Random random(){
        return new Random();
    }
}
