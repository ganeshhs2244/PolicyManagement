package com.m32.Lic_project.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")

public class PolicyDetailsController {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    @PostMapping("bulk-polices")
    public String readAllPolices()
    {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("startAt", System.currentTimeMillis()) // unique job parameters to avoid issues
                    .toJobParameters();

            jobLauncher.run(job, jobParameters);


            return "Import job started successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to start import job: " + e.getMessage();
        }
    }
}
