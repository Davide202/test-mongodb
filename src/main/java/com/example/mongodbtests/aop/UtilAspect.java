package com.example.mongodbtests.aop;


import com.example.mongodbtests.util.FileUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class UtilAspect {
    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }

    @AfterThrowing(value = "@annotation(ErrorHandler)", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint jp, Exception ex){
//        System.out.println("Method Signature: "  + jp.getSignature());
//        System.out.println("Exception: "+ex.getMessage());
        FileUtil.append("Method Signature: " + jp.getSignature() +" Exception: " + ex.getMessage());
    }


}
