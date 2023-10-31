package ex2;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class MessageMain {
	public static void main(String[] args) {
//		allJoinPoint();
		usePoinCut();
	}

	private static void usePoinCut() {
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
//		Target 객체의 정보를 ProxyFactoryBean 저장
		pfBean.setTarget(new MessageImple());
//		Advice를 적용
//		pfBean.addAdvice(new Ex1_MessageAdvice());
//		PointCut 이란 정규표현식 등을 사용해서, Advice를 타겟 객체를 선택하고자 할 때 사용함
//		PointCut을 사용해서 test로 시작하는 메서드만 joinpoint로 설정하겠다.
		NameMatchMethodPointcut pointCut = new NameMatchMethodPointcut();
//		target이 가지고 있는 메서드 이름을 선별할 수 있다.
		pointCut.setMappedName("test*"); //wildcard 사용 가능
//		Advice와 pointCut의 결합 => Advisors
		pfBean.addAdvisor(new DefaultPointcutAdvisor(pointCut, new Ex1_MessageAdvice()));
		Message prMessage = (Message) pfBean.getObject();
//		prMessage.test();
		prMessage.printTest(); // test로 시작하는 메서드만 적용-- 결합(Ex1_MessageAdvice.java)만 안됨.
	}

	private static void allJoinPoint() {
//		AOP의 Advice와 Target의 관계를 관리해주는 객체이다.
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
//		Target 객체의 정보를 ProxyFactoryBean 저장
		pfBean.setTarget(new MessageImple());
//		Advice를 적용
		pfBean.addAdvice(new Ex1_MessageAdvice());
//		요청해보기
		Message prMessage = (Message)pfBean.getObject();
//		prMessage.test();
//		prMessage.test2();
		prMessage.printTest();
	}

}
