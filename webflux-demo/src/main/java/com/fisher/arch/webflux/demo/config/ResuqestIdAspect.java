package com.fisher.arch.webflux.demo.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Aspect
@Component
public class ResuqestIdAspect {

    public static final String REQUEST_ID_KEY = "requestId";
    public static ThreadLocal<String> requestIdThreadLocal = new ThreadLocal<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(ResuqestIdAspect.class);


    /**
     * 环绕于方法开始到结束
     *
     *  method around begin : rewriteUrl
     *  method begin : rewriteUrl, 参数: [], Kind :method-execution
     *  rewrite 方法执行： rewriteUrl()
     *  method around end : rewriteUrl  耗时: 0 ms
     *  method end : rewriteUrl
     *  logAfterReturning: logAfterReturning, return value: null
     *
     * @param proceedingJoinPoint
     */
    @Around("execution(* com.fisher.arch.webflux.demo.controller.*.*(..))")
    public void logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String requestId = UUID.randomUUID().toString();
        requestIdThreadLocal.set(requestId);
        MDC.put("requestId", requestId);
        proceedingJoinPoint.proceed();
    }


    public static String getRequestId(HttpServletRequest request) {
        String requestId = null;
        String parameterRequestId = request.getParameter(REQUEST_ID_KEY);
        String headerRequestId = request.getHeader(REQUEST_ID_KEY);

        if (parameterRequestId == null && headerRequestId == null) {
            LOGGER.info("request parameter 和header 都没有requestId入参");
            requestId = UUID.randomUUID().toString();
        } else {
            requestId = parameterRequestId != null ? parameterRequestId : headerRequestId;
        }
        requestIdThreadLocal.set(requestId);
        return requestId;
    }


}
