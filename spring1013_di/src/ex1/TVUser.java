package ex1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {
//		spring container에 등록된 bean을 getBean()해서 의존성 주입을 구현 함.
//		______________________________중요___________________________________
		ApplicationContext ctx = new GenericXmlApplicationContext("ex1/tv.xml");
		TV tv = ctx.getBean("Ltv", TV.class);
//		____________________________________________________________________
		tv.powerOn();
		tv.powerOff();
		tv.volumeUP();
		tv.volumeDown();
//		____________________________________________________________________		
		tv = ctx.getBean("Stv", TV.class);
		tv.powerOn();
		tv.powerOff();
		tv.volumeUP();
		tv.volumeDown();
		
	}
}
