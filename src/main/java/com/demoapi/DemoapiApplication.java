package com.demoapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoapiApplication.class, args);
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    // https://www.youtube.com/watch?v=bg6r5PIo5FE&list=PLRjWo99hnirwyafPfaxfu0psMR0hUmdQc&index=4
}
