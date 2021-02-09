package com.fisher.demo.tracing.opentracedemo.filters;

import com.fasterxml.uuid.Generators;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import java.io.IOException;

@Configuration
@Order(0)
public class TraceIdFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String traceId = Generators.timeBasedGenerator().generate().toString();
        TraceThreadLocal.setTraceId(traceId);
        MDC.put("traceId", traceId);
        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {
        TraceThreadLocal.clear();
        MDC.clear();
    }
}
