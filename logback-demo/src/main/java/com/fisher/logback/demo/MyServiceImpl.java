package com.fisher.logback.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyServiceImpl.class);

    @Override
    public void load() {
        LOGGER.trace(" 打印日志: {}", "TRACE");
        LOGGER.debug(" 打印日志: {}", "DEBUG");
        LOGGER.info(" 打印日志: {}", "INFO");
        LOGGER.warn(" 打印日志: {}", "WARN");
        LOGGER.error(" 打印日志: {}", "ERROR");
    }
}
