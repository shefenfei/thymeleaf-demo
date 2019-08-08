package com.fisher.logback.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/logback")
@Slf4j
public class LogbackController {

    private static final Logger log = LoggerFactory.getLogger(LogbackController.class);

    @GetMapping("/print")
    public ResponseEntity printLog() {
        log.trace(" 打印日志: {}", "TRACE");
        log.debug(" 打印日志: {}", "DEBUG");
        log.info(" 打印日志: {}", "INFO");
        log.warn(" 打印日志: {}", "WARN");
        log.error(" 打印日志: {}", "ERROR");
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/print1")
    public ResponseEntity printLog1() {
        log.trace(" 打印日志: {}", "TRACE");
        log.debug(" 打印日志: {}", "DEBUG");
        log.info(" 打印日志: {}", "INFO");
        log.warn(" 打印日志: {}", "WARN");
        log.error(" 打印日志: {}", "ERROR");
        return new ResponseEntity<>("", HttpStatus.OK);
    }


    @GetMapping("/test/{arg1}")
    public ResponseEntity testParams(@PathVariable("arg1") String arg1, @RequestParam("code") String code, HttpServletRequest request) {
        System.out.println(request.getRequestURI());
        System.out.println(arg1);
        System.out.println(code);
        return new ResponseEntity<>("", HttpStatus.OK);
    }




    @GetMapping("/getSomeResponse")
    public ResponseEntity getSomeResponse() {
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
