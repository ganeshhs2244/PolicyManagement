package com.m32.Lic_project.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m32.Lic_project.entity.LicPolicy;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;



@Slf4j
public class StepSkipListener implements SkipListener<LicPolicy,Number> {

    Logger logger= LoggerFactory.getLogger(StepSkipListener.class);
    @Override
    public void onSkipInRead(Throwable t) {
        logger.info("Skipped during reading: " + t.getMessage());
        System.out.println("Skipped during reading: " + t.getMessage());

    }

    @Override
    public void onSkipInWrite(Number p, Throwable t) {
        logger.info("Skip in write: " + p + " due to " + t.getMessage());
        System.out.println("Skip in write: " + p + " due to " + t.getMessage());
    }

    @SneakyThrows
    @Override
    public void onSkipInProcess(LicPolicy item, Throwable t) {
        logger.info("Skipped during processing: " + new ObjectMapper().writeValueAsString(item) + " Reason: " + t.getMessage());
        System.out.println("Skipped during processing: " + new ObjectMapper().writeValueAsString(item) + " Reason: " + t.getMessage());

    }

}
