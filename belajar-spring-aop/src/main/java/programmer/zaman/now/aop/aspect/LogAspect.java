package programmer.zaman.now.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LogAspect {

  @Pointcut("target(programmer.zaman.now.aop.service.HelloService)")
  public void helloServiceMethod(){}

  @Before("helloServiceMethod()")
  public void beforeHelloServiceMethod(JoinPoint joinPoint) {
    String className = joinPoint.getTarget().getClass().getName();
    String methodName = joinPoint.getSignature().getName();
    log.info("Before {}.{}()", className, methodName);
  }

  @Around("helloServiceMethod()")
  public Object aroundHelloServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
    String className = joinPoint.getTarget().getClass().getName();
    String methodName = joinPoint.getSignature().getName();

    try {
      log.info("Around Before {}.{}()", className, methodName);
      return joinPoint.proceed(joinPoint.getArgs());
    } catch (Throwable throwable) {
      log.error("Around After Throw {}.{}()", className, methodName);
      throw throwable;
    } finally {
      log.info("Around After {}.{}()", className, methodName);
    }
  }

  @Pointcut("execution(* programmer.zaman.now.aop.service.HelloService.*(java.lang.String))")
  public void pointcutHelloServiceStringParam(){}

  @Before("pointcutHelloServiceStringParam()")
  public void logStringParameter(JoinPoint joinPoint) {
    String value = (String) joinPoint.getArgs()[0];
    log.info("Execute method with parameter: {}", value);
  }

  @Pointcut("execution(* programmer.zaman.now.aop.service.HelloService.*(java.lang.String, java.lang.String))")
  public void pointcutHelloServiceStringParams(){}

  @Before("pointcutHelloServiceStringParams() && args(firstName, lastName)")
  public void logStringParameter2(String firstName, String lastName) {
    log.info("Execute method with parameter: {} {}", firstName, lastName);
  }

  @Pointcut("execution(* programmer.zaman.now.aop.service.*.*(..))")
  public void pointcutServicePackage(){}

  @Pointcut("bean(*Service)")
  public void pointcutServiceBean(){}

  @Pointcut("execution(public * *(..))")
  public void pointcutPublicMethod(){}

  @Pointcut("pointcutServicePackage() && pointcutPublicMethod() && pointcutServiceBean()")
  public void publicMethodForService(){}

  @Before("publicMethodForService()")
  public void logAllPublicServiceMethod(){
    log.info("Log All Public Service Method");
  }
}
