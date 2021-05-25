package com.example.demo;

import com.example.demo.model.Blog;
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
        Blog blog = new Blog();
        blog.setId("1");
        blog.setContent("내용입니다.");
        blog.setTitle("제목입니다.");
        compareESRepository.save(blog);
    }


    @Test
    void contextLoads() {

    }

}
