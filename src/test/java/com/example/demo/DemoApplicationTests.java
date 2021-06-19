package com.example.demo;


import com.example.demo.repository.CompareESRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {
    @Resource
    CompareESRepository compareESRepository;

    @Test
    void test() {

    }


    @Test
    void contextLoads() {

    }

}
