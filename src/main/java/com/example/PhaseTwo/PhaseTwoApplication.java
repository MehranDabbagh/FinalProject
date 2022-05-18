package com.example.PhaseTwo;

import com.example.PhaseTwo.model.entity.*;
import com.example.PhaseTwo.model.service.impl.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@SpringBootApplication
public class PhaseTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhaseTwoApplication.class, args);
    }

    @Component
    static
    class OnStartup implements ApplicationRunner {

        @Override
        public void run(ApplicationArguments args) throws Exception {

        }
    }

}
