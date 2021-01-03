package com.fisher.springboot.autoconfig;

import com.fisher.springboot.autoconfig.service.MyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAutoconfigDemoApplicationTests {


    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MyService myService;



    @Test
    public void contextLoads() {
        logger.trace("这是trace");
        logger.debug("这是debug");
        //springboot 默认给我们的是info级别的日志
        logger.info("这是info");
        logger.warn("这是warn");
        logger.error("这是error");

        myService.sayHello();
    }

}

