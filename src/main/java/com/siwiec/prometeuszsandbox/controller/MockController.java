package com.siwiec.prometeuszsandbox.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.MeterRegistry;

@RestController
public class MockController {
    private final MeterRegistry meterRegistry;

    public MockController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/200")
    public String mock2xxResponse() {
        meterRegistry.counter("call.200", "status", "OK").increment();
        return "Got 200 Response";
    }

    @PostMapping("/alert")
    public void receiveAlert(@RequestBody Map<String, Object> request) throws Exception {
        System.out.println(request);

    }

    @GetMapping("/500")
    public String mock5xxResponse() {
        meterRegistry.counter("call.500", "status", "NOTOK").increment();
        return "Got 5xx Response";
    }

}