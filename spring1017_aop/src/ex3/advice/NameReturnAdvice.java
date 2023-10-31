package ex3.advice;

import org.aspectj.lang.JoinPoint;

// After-returning 시점을 가지고 target의 반환 값을 받을 목적으로 주로 사용 됨!
public class NameReturnAdvice {
//	인자 즉 변수명이 설정과 동일해야 함!, Around 가 아닌 JoinPoint는 생략이 가능하다.
//	Object ret 반환되는 타입과 변수명, 특히 변수명이 설정과 같아야 함 ★★★★★
	
	private void myReturnMethod(JoinPoint jp, Object ret) {
		String namev = jp.getSignature().getName();
		System.out.println("Name: " + namev);
		System.out.println("반환값: " + ret);
		System.out.println("_______________________________");

	}

}
