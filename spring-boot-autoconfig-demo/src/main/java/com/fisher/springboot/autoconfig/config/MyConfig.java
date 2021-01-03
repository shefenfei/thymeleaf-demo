package com.fisher.springboot.autoconfig.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass()
public class MyConfig {


}
