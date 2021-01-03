package com.fisher.starter.autoconfig.service;


import org.springframework.beans.factory.annotation.Autowired;

public class HelloService {

    @Autowired
    HelloProperties helloProperties;

    public String sayHello(String name) {
        return helloProperties.getPrefix() + name + helloProperties.getSuffix();
    }


    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }
}
