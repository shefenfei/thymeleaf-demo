package com.fisher.arch.web.config;

import io.sentry.Sentry;
import io.sentry.SentryClient;
import io.sentry.SentryClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SentryConfig {

    private String dsn = "https://25f8990622f64881b149017c170f08a4@sentry.io/1535396";
//    private String dsn = "http://253a6948e42648599854a165197c72a0@172.24.192.7:9000/4";

    @PostConstruct
    public void init() {
        Sentry.init(dsn);
    }

    @Bean
    public SentryClient sentryClient() {
        return SentryClientFactory.sentryClient(dsn);
    }
}
