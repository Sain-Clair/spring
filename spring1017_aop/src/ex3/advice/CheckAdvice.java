package ex3.advice;
// 공통관심사항

import org.aspectj.lang.ProceedingJoinPoint;

// 어떤 비즈니스로직을 가진 클래스가 소요시간, 지연시간을 측정하고 싶을 때 사용될 Around Advice
// Around Advice - joinpoint 객체가 반드시 메서드의 인자로 선언해야 한다.★★★★★
// 반드시 ProceedingJoinPoint 객체 선언
public class CheckAdvice {
	private void checkTime(ProceedingJoinPoint pjp)throws Throwable{
		long start = System.currentTimeMillis();
		
//		target 객체의 메서드를 호출
		pjp.proceed();
		
		long end = System.currentTimeMillis();
		System.out.println("수행 된 속도:" + (end- start)+"초");
	}
}
