package com.fisher.demo.tracing.opentracedemo.controller;

import com.fisher.demo.tracing.opentracedemo.service.TraceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/trace")
@RestController
public class TraceDemoController {

    private Logger logger = LoggerFactory.getLogger(TraceDemoController.class);
    @Autowired
    private TraceService traceService;

    @GetMapping("/test")
    public String trace() {
//        String traceId = TraceThreadLocal.getTraceId();
        logger.info("开始处理请求");
        traceService.doSomething();

        /*for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                traceService.chargeNum();
            }).start();
        }*/

        traceService.chargeNum();
        logger.info("处理请求完成");
        return "success";
    }

    @PostMapping("/save")
    public String saveStock() {
//        traceService.doSomething();
        return ";";
    }
}
