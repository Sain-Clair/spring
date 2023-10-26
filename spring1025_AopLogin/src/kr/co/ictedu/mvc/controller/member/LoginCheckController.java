package kr.co.ictedu.mvc.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ictedu.mvc.dao.MemberDaoInter;
import kr.co.ictedu.mvc.dto.MemberVO;

@Controller
@RequestMapping("/login")
public class LoginCheckController {
	@Autowired
	private MemberDaoInter MemberDaoInter;
	
	@RequestMapping("/loginForm") // Get, Post 둘다 가능
	public String loginForm() {
		return "member/loginForm";
	}
	// loginProcess : 인증, 세션을 저장
	@PostMapping("/loginProcess")
	public ModelAndView loginfProcess (
			HttpSession session,
			HttpServletRequest request,
			MemberVO vo, @RequestHeader("User-Agent") String userAgent) { //로그인정보를 받아온다?
	
		ModelAndView mav = new ModelAndView("redirect:/main");  // 로그인 시 갈 페이지?
		MemberVO dto = MemberDaoInter.loginCheck(vo);
		if(dto == null) {
			mav.setViewName("error/paramException");
			mav.addObject("sessionID", dto.getId());
			
		}else {
			session.setAttribute("sessionName", dto.getName());
			session.setAttribute("sessionID", dto.getId());
			System.out.println("로그인 실행! 및 세션 저장 => Proceeding Call");
		}
		
		return mav;
	}
	
	// logout : session을 삭제
	@GetMapping("/logout")
	public ModelAndView loginfoutProcess(HttpSession session,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			session.removeAttribute("sessionName");
			session.removeAttribute("sessionID");
			mav.setViewName("redirect:/main");
			System.out.println("로그아웃 실행! 및 세션 삭제 => Proceeding Call");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	
}
