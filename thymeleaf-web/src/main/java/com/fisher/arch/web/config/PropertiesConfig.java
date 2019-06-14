package com.fisher.arch.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.List;

@Validated
@Component
@ConfigurationProperties(prefix = "thymeleaf.app")
public class PropertiesConfig {

    @NotNull
    private String name;
    private String author;
    private String defaultSubject;
    private boolean enable;
    private List<String> serverList;
    private Duration casttime;

    private String test;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDefaultSubject() {
        return defaultSubject;
    }

    public void setDefaultSubject(String defaultSubject) {
        this.defaultSubject = defaultSubject;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<String> getServerList() {
        return serverList;
    }

    public void setServerList(List<String> serverList) {
        this.serverList = serverList;
    }

    public Duration getCasttime() {
        return casttime;
    }

    public void setCasttime(Duration casttime) {
        this.casttime = casttime;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
