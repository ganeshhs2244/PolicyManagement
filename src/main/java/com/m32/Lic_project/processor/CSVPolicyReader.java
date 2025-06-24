package com.m32.Lic_project.processor;

import com.m32.Lic_project.entity.LicPolicy;
import com.m32.Lic_project.mapper.PolicyMapper;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;



@Component
public class CSVPolicyReader{

    @Value("${file.path}")
    private String filePath;

    public FlatFileItemReader<LicPolicy> createReader() {
        FlatFileItemReader<LicPolicy> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource(filePath));
        reader.setLinesToSkip(5);

        reader.setLineMapper(new DefaultLineMapper<LicPolicy>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("id","familyCode", "policyHolderName", "DOB", "mobile", "email", "address",
                        "policyNumber", "agencyCode", "commecementDate", "plan", "Term", "PPT",
                        "sumAssured", "mode", "FUPDate", "Premium", "nominee");
            }});
            setFieldSetMapper(new PolicyMapper());
        }});

        return reader;
    }


}
