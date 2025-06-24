package com.m32.Lic_project.processor;

import com.m32.Lic_project.entity.LicPolicy;
import com.m32.Lic_project.repository.PolicyRepository;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Component
public class PolicyWriter implements ItemWriter<LicPolicy> {

    @Autowired
    private PolicyRepository PolicyRepository;

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public void write(Chunk<? extends LicPolicy> chunk) throws Exception {
         PolicyRepository.saveAll(chunk);
    }
}

