package com.lavacorp.inven3;

import org.jdbi.v3.spring5.EnableJdbiRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJdbiRepositories
public class Inven3Application {

    public static void main(String[] args) {
        SpringApplication.run(Inven3Application.class, args);
    }

}
