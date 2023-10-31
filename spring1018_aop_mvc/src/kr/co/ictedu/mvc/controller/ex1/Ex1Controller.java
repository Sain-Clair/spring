package kr.co.ictedu.mvc.controller.ex1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dto.MemberDTO;

// ModelAndView : 모델이 실행되고 난 후, 컨트롤 서블릿에게 View이름, 값(forward)을 보냄
// View이름, 동적으로 모델의 값이 달라질 수도 있을 때 사용을 권장
@Controller
public class Ex1Controller {
//	@GetMapping("/memForm")
//	public ModelAndView memForm() {
//		ModelAndView mav = new ModelAndView("member/memberForm");
////		mav.setView("member/memberForm");
//		return mav;
//	}
	// 단순하게 뷰만 반환: String, 메소드 이름
	@GetMapping("/memForm")
	public String memForm() {
		return "member/memberForm";
	}
	
	// id 중복체크
	// Model m : View로 값을 전달할 때. ex.HelloController.java) mav.addObject("msg","<h1> 안녕하세요. 첫번째 Spring MVC</h1>");	 과 같은 역할
	// idcheck?id=xman
	@GetMapping("/idcheck") // 대소문자 구분함
	public String idCheck(Model m, String id) {
//		String id = request.getParameter("id");
		String dbid = "xman";
		if(dbid.equals(id)) {
//			m.addAttribute("res", "존재하는 아이디 입니다.");
			m.addAttribute("res", 1);
		}else {
//			m.addAttribute("res", "사용가능한 아이디 입니다.");
			m.addAttribute("res", 0);
		}
		return "member/idchk";
	}
	
	// 이름: 윤승현, 제출형식: 댓글 스크린 샷
	// memberForm 입력된 파라미터를 모두 sysout으로 각각 콘솔에 출력하고
	// member/success.jsp에서는 이 데이트를 각각 EL로 출력하시오.	
	@PostMapping("/memberIn_Back") 
	public String memberIn(Model m, String id, String pwd, String uname, String chk) {
		System.out.println("아이디:" +id);
		System.out.println("비밀번호: " + pwd);
		System.out.println("이름: " + uname);
		System.out.println("체크박스:" + chk);
		
		m.addAttribute("id",id);
		m.addAttribute("pwd",pwd);
		m.addAttribute("uname",uname);
		m.addAttribute("chk",chk);
		return "member/success";
	}
	// DTO로 파라미터를 바인딩 시켜준다.
	// form or get
	// name="파라미터이름" == DTO의 property
	@PostMapping("/memberIn")
	public String memberIn(Model m, MemberDTO vo) {
		System.out.println("___________________________");
		System.out.println("아이디:" +vo.getId());
		System.out.println("비밀번호: " + vo.getPwd());
		System.out.println("이름: " + vo.getUname());
		for(String e: vo.getChk()) {
			System.out.println(e);
		}
		
//		m.addAttribute("id",vo.getId());
//		m.addAttribute("uname",vo.getUname());
		m.addAttribute("vo", vo);
		return "member/success2";
	}
}
