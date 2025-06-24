package com.m32.Lic_project.listener;

import com.m32.Lic_project.entity.LicPolicy;
import jakarta.servlet.WriteListener;

import java.io.IOException;

public class StepWriteListener implements WriteListener{
    @Override
    public void onWritePossible() throws IOException {
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }
}
