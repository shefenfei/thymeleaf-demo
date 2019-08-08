package com.fisher.arch.bytebuddy;

import java.lang.instrument.Instrumentation;

public class MyPremainAgent {

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("invoke mypremain");
    }
}
