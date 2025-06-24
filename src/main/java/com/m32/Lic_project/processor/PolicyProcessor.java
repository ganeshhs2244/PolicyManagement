package com.m32.Lic_project.processor;

import com.m32.Lic_project.entity.LicPolicy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class PolicyProcessor implements ItemProcessor<LicPolicy, LicPolicy> {
    @Override
    public LicPolicy process(LicPolicy policy) {
//        user.setName(user.getName().toUpperCase()); // Example
        return policy;
    }
}
