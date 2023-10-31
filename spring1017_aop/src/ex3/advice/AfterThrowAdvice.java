package ex3.advice;

public class AfterThrowAdvice {
//	JoinPoint jp 생략 해도 됨!
//	after-returning 마찬가지로 지역 변수의 이름과 설정이 같아야 함!
	private void commThrow(Exception ew) {
		System.out.println("예외 메세지:" + ew.getMessage());
	}

}
