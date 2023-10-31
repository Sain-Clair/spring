package exam;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
@Aspect
public class BeforeAdvice {
//	@Autowired
//	private LoginTest loginTest;
	@Before("execution(* exam.SecurityImple.*(..))")
	public void print() {
		System.out.println("sdfsdfsdfsd");
	}
}
