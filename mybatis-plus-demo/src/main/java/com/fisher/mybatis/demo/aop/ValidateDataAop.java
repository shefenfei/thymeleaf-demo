package com.fisher.mybatis.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Aspect
@Component
public class ValidateDataAop {

    @Around("execution(* com.fisher.mybatis.demo.controller..*(..))")
    public Object validateAround(ProceedingJoinPoint point) {
        Object[] args = point.getArgs();
        try {
            for (Object arg : args) {
                if (arg instanceof BindingResult) {
                    if (((BindingResult) arg).hasErrors()) {
                        return ResponseEntity.ok().body(((BindingResult) arg).getFieldError().getDefaultMessage());
                    }
                }
            }
            Object proceed = point.proceed(args);
            return proceed;
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        } finally {

        }
    }
}
