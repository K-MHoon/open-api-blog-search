package com.blog.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BlogSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogSearchApplication.class, args);
    }

}
