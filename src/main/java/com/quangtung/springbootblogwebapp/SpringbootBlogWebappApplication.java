package com.quangtung.springbootblogwebapp;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootBlogWebappApplication {
    @Bean
    public ModelMapper modelMapper() {
        // Tạo object và cấu hình
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringbootBlogWebappApplication.class, args);
    }

}
