package kr.co.ictedu.mvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

// HelloController는 예전에 모델에 해당이 된다
// DispatcherServlet 란 컨트롤 서블릿이 스프링 컨테이너 의해서 Model을 주입 받아서 실행
// HandleMapping 이 요청 사항을 분석 해준다. ★★★★★
// 이와 같이 @Controller를 선언해줘야 DispatcherServlet에 존재하는 스프링 컨테이너가 
// 참조하는 ict01-servlet.xml에서 <context:component-scan 방식으로 bean으로 등록이 됨 
@Controller
public class HelloController {
	
	// HandlerMapping
	// @RequestMapping, @GetMapping,@PostMapping ... 애매할땐 RequestMapping
	// http://localhost:8080/spring/hello
	
//	@RequestMapping(value ="/hello", method = RequestMethod.GET)
	public ModelAndView myHello() {
		ModelAndView mav = new ModelAndView();
//		ViewResolver에 의해서 view의 위치와 .jsp를 붙여서 완성한 후
//		DispatcherServlet 전달하면 응답을 해주는 방식
		mav.setViewName("hello"); // ViewName을 설정
		mav.addObject("msg","<h1> 안녕하세요. 첫번째 Spring MVC</h1>");
		mav.addObject("today","<h2>"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"</h2>");
		return mav;
	}
}
