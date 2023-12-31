package kr.co.ictedu.mvc.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ictedu.mvc.dao.MemberDaoInter;
import kr.co.ictedu.mvc.dto.MemberVO;
import kr.co.ictedu.mvc.dto.MyLoginLoggerVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	
	@Autowired
	private MemberDaoInter dao;
	
	//회원 가입 폼
	@GetMapping("/memForm")
	public String memForm () {
		return "member/memberForm";
	}
	// 아이디 중복처리
	@GetMapping("/idcheck")
	@ResponseBody
	public String idcheck(
			@RequestParam(required = true) String id ) {
//		String [] dbid = {"xman","bigdaddy","postman"};
		int res = dao.idCheck(id);
//		for(String e:dbid) {
//			if(e.equals(id)) {
//				res = 1;
//				break;
//			}
//		}
		System.out.println("res : " + res);
		return String.valueOf(res);
	}
	@PostMapping("/memberIn")
	public String memberIn(Model m , MemberVO vo, HttpServletRequest request) {
		vo.setReip(request.getRemoteAddr());
		dao.add(vo);
		m.addAttribute("name",vo.getName());
		m.addAttribute("id",vo.getId());
		
		return "member/mysuccess";  //redirect와 forward 차이
	}

//	@RequestMapping("/mypage")
//	public String mypage(HttpSession session, Model model) {
//		String gotoPage ="";
//		if(session.getAttribute("sessionID") == null) {
//			gotoPage = "redirect:/main";
//		}else {
//			gotoPage= "member/mypage";
//		}
//		String idn = (String)session.getAttribute("sessionID");
//		List<MyLoginLoggerVO> list =dao.logList(idn);
//		model.addAttribute("list",list);
//		return gotoPage;
//	}
	
	@RequestMapping("/mypage")
	public String myPage(HttpSession session, Model m ) {
		
		if(session.getAttribute("sessionID") == null) {
			return("redirect:/main");
		}
			List<MyLoginLoggerVO> list = dao.logList(session.getAttribute("sessionID").toString());
			int max = dao.MaxloginNum(session.getAttribute("sessionID").toString());
			m.addAttribute("list",list);
			m.addAttribute("max",max);
		return "member/mypage";
		
	}
	
	
}
