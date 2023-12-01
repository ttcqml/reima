package com.micro.reima.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class RequestLogAspect {

    /**
     * 切入点
     */
    @Pointcut("execution(* com.micro.reima.controller.*.*Controller.*(..))")
    public void entryPoint() {
        // 无需内容
    }

    @Before("entryPoint()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] parameters = joinPoint.getArgs();
            log.info(">>>请求:IP:{},URL:{},{}.{},请求参数:{}",request.getRemoteAddr(),request.getRequestURL().toString(),
                    className,methodName,JSON.toJSONString(parameters));
        } catch (Throwable e) {
            log.info("around " + joinPoint + " with exception : " + e.getMessage());
        }

    }

    @Around("entryPoint()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long time = System.currentTimeMillis() - startTime;
        log.info("<<<返回:{}.{},返回参数:{},耗时:{} ms",className,methodName,JSON.toJSONString(result),time);
        return result;
    }

    @AfterThrowing(pointcut = "entryPoint()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        // 通过request获取登陆用户信息
        // HttpServletRequest request = ((ServletRequestAttributes)
        // RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] parameters = joinPoint.getArgs();
            log.error("异常方法:" + className + "." + methodName + "();参数:" + JSON.toJSONString(parameters));
            log.error("异常信息:" + e.getMessage());
        } catch (Exception ex) {
            log.error("异常信息:{}", ex.getMessage());
        }
    }
}
