package ex2;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

// 내부 데이터 수집 시 사용?
// Advice 클래스: <공통관심 사항>을 정의한 클래스
public class Ex1_MessageAdvice implements MethodInterceptor{
//	 MethodInvocation invocation 타겟의 메서드의 호출 정보를 가지고 있는 객체를 인자로 주입받음
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("🤷‍♀️🤷‍♀️🤷🤷‍♀️🤷‍♀️🤷🤷‍♀️🤷‍♀️🤷‍♀️"); // 선행
		Object ref = invocation.proceed(); // target메서드를 호출 // test로 시작되는 메서드
		System.out.println("🤐🤐🤐🤐🤐🤐🤐🤐🤐"); // 후
		return ref;
 	}
	
}



