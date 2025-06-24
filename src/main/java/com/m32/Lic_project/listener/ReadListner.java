package com.m32.Lic_project.listener;

import com.m32.Lic_project.entity.LicPolicy;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Component
public class ReadListner implements ItemReadListener<LicPolicy> {

    @Override
    public void onReadError(Exception ex) {
        System.out.println(" onReadError ---> "+ex.getMessage());

    }
}
