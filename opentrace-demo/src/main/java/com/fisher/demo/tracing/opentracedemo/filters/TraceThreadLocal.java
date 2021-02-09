package com.fisher.demo.tracing.opentracedemo.filters;

public class TraceThreadLocal  {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void setTraceId(String value) {
        THREAD_LOCAL.set(value);
    }

    public static String getTraceId() {
        return THREAD_LOCAL.get();
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }
}
