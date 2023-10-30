package kr.co.ictedu.mvc.controller.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

//@Component // bean 등록
//@Aspect // proxy Factory
public class LoginAdviceBack { 
	private String userAgent;
	@Around("execution(* kr.co.ictedu.mvc.controller.member.LoginCheckControllerBack.loginf*(..))")
	public ModelAndView loginLogger(ProceedingJoinPoint jp) {
		// 전처리
		// 1. 로그인, 로그아웃의 메서드가 호출된 후, 반환값을 받기 위해서 선언
		ModelAndView rpath= null;
		
		// 2. 메서드의 이름을 가져와서 로그인과 로그아웃을 구별한다.
		String methodName = jp.getSignature().getName(); // 메서드의 이름(loginf~~ 담겨있음)
		System.out.println("AOPLog : methodName =>" + methodName);
		
		// 3. Joinpoint로 부터 타겟 객체의 메서드의 인자값 받아오기
		Object[] fd = jp.getArgs();
		if(methodName.equals("loginfProcess")) {
			// 비즈니스 모델의 메서드를 호출 ★★★★
			try {
				rpath = (ModelAndView) jp.proceed(); // -> loginf*()
				
			} catch (Throwable e) {
				e.printStackTrace();
			}
			System.out.println("후처리 로깅작업");
			HttpSession session=(HttpSession)fd[0];
			String uid = (String)session.getAttribute("sessionID");
			String reip = ((HttpServletRequest)fd[1]).getRemoteAddr();
			userAgent = (String)fd[3];
			System.out.println("uid => " + uid);
			System.out.println("reip 출력 => "+reip);
			System.out.println("userAgent 출력 => "+userAgent);
		}else if(methodName.equals("loginfoutProcess")) {
			// 전처리를 사용한 로깅
			System.out.println("전처리 로깅작업");
			HttpSession session=(HttpSession)fd[0];
			String uid = (String)session.getAttribute("sessionID");
			String reip = ((HttpServletRequest)fd[1]).getRemoteAddr();
			System.out.println("uid => " + uid);
			System.out.println("reip 출력 => "+reip);
			System.out.println("userAgent 출력 => "+userAgent);
			// 비즈니스 모델의 메서드를 호출 ★★★★
			try {
				rpath = (ModelAndView) jp.proceed(); // -> loginf*()

			} catch (Throwable e) {
				e.printStackTrace();
			}
		}else {
			
		}
//		rpath.setViewName("redirect:/upBoard/upList");
		
		return rpath;
		
		
	}
}
