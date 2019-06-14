package com.fisher.arch.web.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

@Aspect
@Slf4j
@Configuration
public class ThymeleafAspect {

    @Before("execution(* com.fisher.arch.web.controller.IndexController.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("method begin : {}, 参数: {}, Kind :{} ", joinPoint.getSignature().getName(), joinPoint.getArgs(), joinPoint.getKind());
    }


    @After("execution(* com.fisher.arch.web.controller.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("method end : {}", joinPoint.getSignature().getName());
    }


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
    @Around("execution(* com.fisher.arch.web.controller.IndexController.rewriteUrl())")
    public void logAround(ProceedingJoinPoint proceedingJoinPoint) {
        long methodExecuteTime = System.currentTimeMillis();
        log.info("method around begin : {}", proceedingJoinPoint.getSignature().getName());
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long methodExecuteFinishTime = System.currentTimeMillis();
        log.info("method around end : {}  耗时: {} ms", proceedingJoinPoint.getSignature().getName(), methodExecuteFinishTime - methodExecuteTime);
    }


    /**
     * 运行的带注释的方法正常执行并且不抛出任何错误/异常， 有时您需要访问实际返回值 - 从方法返回的值，您可以使用注释returning内的属性获取返回值
     * @param e
     */
    @AfterReturning(value = "execution(* com.fisher.arch.web.controller.*.*(..))" ,returning = "e")
    public void logAfterReturning(Object e) {
        log.info("logAfterReturning: {}, return value: {}", "logAfterReturning", e);
    }



//    @AfterThrowing(value = "execution(* com.fisher.arch.web.controller.*.*(..))", throwing = "ex")
    public void logAfterThrowing(Exception ex) throws Throwable{
        log.info("logAfterThrowing: {}, exception Messgae: {}", "logAfterThrowing", ex.getMessage() );
    }
}
