package com.example.demo.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

@Data
@Configuration
public class BoardReturn<T> {
    private T result;
}
