package com.m32.Lic_project.config;


import com.m32.Lic_project.entity.LicPolicy;
import com.m32.Lic_project.listener.ReadListner;
import com.m32.Lic_project.listener.StepSkipListener;
import com.m32.Lic_project.processor.CSVPolicyReader;
import com.m32.Lic_project.processor.PolicyProcessor;
import com.m32.Lic_project.processor.PolicyWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import java.net.BindException;


@Configuration
public class BatchConfig {

    @Value("${file.path}")
    private String filePath;

    @Autowired
    private PolicyProcessor processor;
    @Autowired
    private PolicyWriter writer;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private CSVPolicyReader csvPolicyReader;

    @Autowired
    private ReadListner readListner;


    @Bean
    public Step step() {
        return new StepBuilder("excel-step", jobRepository)
                .<LicPolicy, LicPolicy>chunk(50, transactionManager)
                .reader(createReader())
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(2000)
                .skipPolicy(skipPolicy())
                .taskExecutor(taskExecutor())
                .listener(skipListener())
                .listener(readListner)
                .build();
    }
    @Bean
    public Job job() {
        return new JobBuilder("excel-import-job", jobRepository)
                .start(step())
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<LicPolicy> createReader()
    {
        return csvPolicyReader.createReader();
    }
    @Bean
    public TaskExecutor taskExecutor()
    {

        SimpleAsyncTaskExecutor asyncTaskExecutor=new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(5);
        return asyncTaskExecutor;
    }

    @Bean
    public SkipListener skipListener()
    {
        return new StepSkipListener();
    }

    @Bean
    public SkipPolicy skipPolicy() {
        return new SkipPolicy() {
            @Override
            public boolean shouldSkip(Throwable t, long skipCount) throws SkipLimitExceededException {
                return true;
            }
        };
    }




}
