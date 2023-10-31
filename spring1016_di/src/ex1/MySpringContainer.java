package ex1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MySpringContainer {
	public static void main(String[] args) {
		// 의존 관계가 형성된 2객체 생성
		ApplicationContext ctx = new GenericXmlApplicationContext("ex1/ex1_ref.xml");
//		A를 찾아와서 B의 자원을 활용하는 개념이다.
		MyTestA refA = ctx.getBean("testA",MyTestA.class);
		System.out.println(refA.prutUseB());
	}

}
