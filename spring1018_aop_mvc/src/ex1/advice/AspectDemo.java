package ex1.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
// <aop:aspectj-autoproxy/> 의해서 ProxyFactory로 관리 되는 설정
import org.springframework.beans.factory.annotation.Autowired;
@Aspect
public class AspectDemo {
	@Autowired
	private MyPublic myPublic;
	
	@Before("execution(* ex1.DaoImple.first*(..))")
	private void myBefore() {
		System.out.println("Today: " + myPublic.todayMethod());
	}
	@AfterReturning(pointcut = "execution(* ex1.DaoImple.second*(..))", returning = "ret")
	private void myReturnMethod(JoinPoint jp, Object ret) {
		String namev = jp.getSignature().getName();
		System.out.println("Name: " + namev);
		System.out.println("반환값: " + ret);
		System.out.println("_______________________________");
	}
	@Around(value = "execution(* ex1.DaoImple.*(..))")
	private void checkTime(ProceedingJoinPoint pjp)throws Throwable {
		long start = System.currentTimeMillis();
		pjp.proceed();
		long end = System.currentTimeMillis();
		System.out.println("시간시간:" + (end-start));

	}
	
	

}
