package ex3_과제;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class MessageMain2 {
	public static void main(String[] args) {
		usePoinCut();
	}

	private static void usePoinCut() {
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		pfBean.setTarget(new MessageImple2());
		NameMatchMethodPointcut pointCut = new NameMatchMethodPointcut();
//		pointCut.setMappedName("test*"); //wildcard 사용 가능
		pointCut.setMappedName("print*"); //wildcard 사용 가능
		pfBean.addAdvisor(new DefaultPointcutAdvisor(pointCut, new Ex2_MessageAdvice2()));
		Message2 prMessage = (Message2) pfBean.getObject();
//		prMessage.test();
//		prMessage.test2();
//		prMessage.test3("");
		prMessage.print();
		prMessage.printTest();
		
		
	}
}
