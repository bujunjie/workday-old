package com.junjie.util;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author jbu
 */
@Component
@Aspect
public class MyAspect {

  private static final Logger log = Logger.getLogger(MyAspect.class);

  @Around("execution(* com.junjie.model..*.*(..))")
  public Object logTimeMethod(ProceedingJoinPoint joinPoint) throws Throwable {

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    Object retVal = joinPoint.proceed();

    stopWatch.stop();

    StringBuffer logMessage = new StringBuffer();
    logMessage.append(joinPoint.getTarget().getClass().getName());
    logMessage.append(".");
    logMessage.append(joinPoint.getSignature().getName());
    logMessage.append("(\n\t");
    // append args
    Object[] args = joinPoint.getArgs();
    for (int i = 0; i < args.length; i++) {
      logMessage.append(args[i]).append(",\n\t");
    }
    if (args.length > 0) {
      logMessage.deleteCharAt(logMessage.length() - 1);
      if (logMessage.length() > 255) {
        logMessage.delete(255, logMessage.length()).append("...");
      }
    }

    logMessage.append(")\n");
    logMessage.append(" execution time: ");
    logMessage.append(stopWatch.getTotalTimeMillis());
    logMessage.append(" ms");
    log.debug(logMessage.toString());
    return retVal;
  }

//  @AfterReturning(pointcut = "execution(* com.junjie.model..*.*(..))", returning = "retVal")
    public void logAfterMethod(JoinPoint joinPoint, Object retVal) {
      log.debug("Method returned:"+retVal);
    }

}