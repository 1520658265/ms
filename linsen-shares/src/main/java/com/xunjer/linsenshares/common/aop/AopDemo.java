package com.xunjer.linsenshares.common.aop;

import com.xunjer.linsenshares.common.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author yuansheng
 * @Title: AopDemo
 * @Description:
 * @date 2020/7/115:34
 */
@Aspect
@Component
@Slf4j
public class AopDemo {

//    @Pointcut("execution(* com.xunjer.cn.linsen.controller..*.*(..))")
//    public void demoController(){
//
//    }

    @Pointcut("@annotation(com.xunjer.linsenshares.common.annotation.LogDemo)")
    public void ann(){

    }
//
//    @Before("ann()")
//    public void a(){
//        log.info("1111");
//    }

    @Around("ann()")
    public Object demo(final ProceedingJoinPoint point){
        long start = System.currentTimeMillis();
        //可以获得正在执行的方法+方法的参数
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        Object[] args = point.getArgs();
        try {
            Object proceed = point.proceed();
            long end = System.currentTimeMillis();
            log.info("用时"+(end-start));
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return CommonUtil.NotAuth();
        }
    }
}
