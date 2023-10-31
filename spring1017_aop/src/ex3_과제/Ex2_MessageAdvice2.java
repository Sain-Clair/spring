package ex3_과제;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class Ex2_MessageAdvice2 implements MethodInterceptor{
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println();
		long start = System.currentTimeMillis();  //선

		
		Object ref = invocation.proceed(); // target메서드를 호출 // test로 시작되는 메서드
		
		
		long end = System.currentTimeMillis(); //후
		System.out.println("소요시간11: " + (end - start));
		
		return ref;
	}
	
}
