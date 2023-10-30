package kr.co.ictedu.mvc.controller.advice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ictedu.mvc.dao.MemberDaoInter;
import kr.co.ictedu.mvc.dto.MemberVO;
import kr.co.ictedu.mvc.dto.MyLoginLoggerVO;

@Component
@Aspect
public class LoginAdvice {
	
	@Autowired
	private MemberDaoInter dao;
	
	// 접속환경
	private String userAgent, resAgent;
	
	@Around("execution(* kr.co.ictedu.mvc.controller.member.LoginCheckController.loginf*(..))")
	public ModelAndView loginLogger(ProceedingJoinPoint jp) {
		///// <전처리>
		// 1. 로그인,로그아웃의 메서드가 호출되고 난 반환값을 받기 위해서 선언
		ModelAndView rpath = null;
		
		// 2. 메서드의 이름을 가져와서 로그인과 로그아웃을 구분
		String methodName = jp.getSignature().getName();
		
		// 3. JoinPoint로 부터 타겟 객체의 메서드 인자값 받아오기
		Object[] fd = jp.getArgs();
		
		if (methodName.equals("loginfProcess")) { //들어오는 메서드가 로그인일시
			///// <비즈니스모델의 메서드 호출 *****>
			try {
				rpath = (ModelAndView) jp.proceed(); // = loginf*()
			} catch (Throwable e) {
				e.printStackTrace();
			}
			System.out.println("***** (AOPLOG) methodName : " + methodName + "*****");
			System.out.println("후처리 로깅작업");
			HttpSession session = (HttpSession) fd[0];
			String uid = (String) session.getAttribute("sessionID");
			String reip = ((HttpServletRequest)fd[1]).getRemoteAddr();
			userAgent = (String) fd[3];
			resAgent = patternUserAgent(userAgent);
			System.out.println("uid : " + uid);
			System.out.println("reip: "+ reip);
			System.out.println("userAgent : " + userAgent);
			System.out.println(patternUserAgent(userAgent));
			System.out.println("=================================");
			
			if(uid != null) {
				MyLoginLoggerVO lvo = new MyLoginLoggerVO();
				lvo.setIdn(uid);
				lvo.setStatus("login");
				lvo.setReip(reip);
				lvo.setUagent(resAgent);
				dao.addLoginLogging(lvo);
			} else {
				System.out.println("후처리 실행 안함");
			}
			
		} else if (methodName.equals("loginfoutProcess")) { //들어오는 메서드가 로그아웃일시
			// 전처리를 사용한 로깅
			System.out.println("***** (AOPLOG) methodName : " + methodName + "*****");
			System.out.println("전처리 로깅작업");
			HttpSession session = (HttpSession) fd[0];
			String reip = ((HttpServletRequest)fd[1]).getRemoteAddr();
			String uid = (String) session.getAttribute("sessionID");
			System.out.println("uid : " + uid);
			System.out.println("reip: "+ reip);
			System.out.println("userAgent : " + userAgent);
			MyLoginLoggerVO lvo = new MyLoginLoggerVO();
			lvo.setIdn(uid);
			lvo.setStatus("logout");
			lvo.setReip(reip);
			lvo.setUagent(resAgent);
			dao.addLoginLogging(lvo);
			
			///// <비즈니스모델의 메서드 호출 *****>
			try {
				rpath = (ModelAndView) jp.proceed(); // = loginf*()
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
		} else {
			
		}
	
		///// <후처리>
		return rpath;
	}
	
	public String patternUserAgent (String userAgent) {
		// userAgent = "Mozilla/5.0 (Linux; Android 13; SM-S906N Build/TP1A.220624.014; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/118.0.0.0 Mobile Safari/537.36;KAKAOTALK 2610380";
		
		Pattern mp = Pattern.compile("(Mobil|Andriod|iPone|iPod|Macintosh)");
		Matcher mc = mp.matcher(userAgent);
		
		boolean res = mc.find();
		System.out.println(res);
		
		StringBuilder sb = new StringBuilder();
		
		if (res) {
			System.out.println("모바일접속");
			sb.append("Mobile").append("/");
		} else {
			System.out.println("pc접속");
			sb.append("PC").append("/");
		}
		
		// \\d : 숫자
		// + : 바로 앞의 요소가 하나 이상 반복됨
		Pattern mp1 = Pattern.compile("(Windows NT [\\d.]+|Android [\\d.]+|iPhone)");
		Matcher mc1 = mp1.matcher(userAgent);
		
		if (mc1.find()) {
			String device = mc1.group();
			System.out.println(device);
			sb.append(device);
		}
		
		return sb.toString();
	}
}