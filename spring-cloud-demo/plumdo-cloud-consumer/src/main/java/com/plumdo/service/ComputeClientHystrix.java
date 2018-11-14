package com.plumdo.service;

import org.springframework.stereotype.Component;

@Component
public class ComputeClientHystrix implements ComputeClient {
    @Override
    public String hello() {
        return "hello-error";
    }
}