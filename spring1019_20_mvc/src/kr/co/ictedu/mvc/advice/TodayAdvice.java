package kr.co.ictedu.mvc.advice;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
// 일반적인 Bean으로  Aspect를 등록한다. @Component
@Component
@Aspect
public class TodayAdvice {
	@Before("execution(* kr.co.ictedu.mvc.controller.main.TodayMy*.today*(..) )")
	public void todayBeforeMethod() {
		System.out.println("오늘날짜");
	}
	
	// 연습문제
	// 해당 모델이 수행한 후의 반환 값을 받아서 다시 컨트롤러로 그 값을 반환 해주는 
	// AfterReturning Advice를 정의하시오.
	// hint: 해당 모델. target. public ModelAndView todayProfile(), public ModelAndView todayWork()
	// 시점 : AfterReturning 결정
	// 반환값: - ModelAndView
	// Model이 반환하는 ModelAndView 가로채서 ,
	// mav.addObject("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	// 작성자: 윤승현
	@AfterReturning(pointcut = "execution(* kr.co.ictedu.mvc.controller.main.TodayMy*.today*(..) )", returning = "ret")
	public ModelAndView todayReturning(JoinPoint jp, ModelAndView ret) {
		// 타겟의 비지니스 로직이 수행된 이후의 반환된 ModelAndView 값
		// view 전송될 값, view의 이름이 결정된 상태임
		// advice를 적용
		// WAS가 service에 주입시켜주는request를 받음
		ServletRequestAttributes sr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sr.getRequest();
		ret.addObject("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		ret.addObject("reip", request.getRemoteAddr());
		return ret;
	}
	// 둘다 됨
	//	public Object todayReturning(ModelAndView ret) { 
//		ret.addObject("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));		
//		return ret;
//	}
}
