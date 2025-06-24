package com.m32.Lic_project.jobSchedular;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyJobSchedular implements CommandLineRunner {


    // can call any job that you need to start once the application start
    @Override
    public void run(String... args) throws Exception {
        log.info("Application started ");

    }


}
