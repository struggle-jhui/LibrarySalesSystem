package com.epam.librarysalessystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LibrarySalesSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibrarySalesSystemApplication.class, args);
    }
}
