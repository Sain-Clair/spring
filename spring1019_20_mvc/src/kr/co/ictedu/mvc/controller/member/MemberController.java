package kr.co.ictedu.mvc.controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ictedu.mvc.dto.MemberDTO;

//ModelAndView : 모델이 실행하고 난 후 컨트럴서블릿에게 View이름,값(forward)
//View이름 , 동적으로 모델의 값이 달라질 수도 있을 때 사용을 권장 
@Controller
public class MemberController {
	/*
	 * @GetMapping("/memForm") public ModelAndView memForm() { ModelAndView mav =
	 * new ModelAndView("member/memberForm");
	 * //mav.setViewName("member/memberForm"); return mav; }
	 */
	// 단순하게 뷰만 반환 : String
	@GetMapping("/memForm")
	public String memForm() {
		return "member/memberForm";
	}

	// id중복 체크
	// Model m : View로 값을 전달할 때 : mav.addObject
	// 여러분이 idcheck?id=xman
	// @RequestMapping(value = "/idcheck")
	// HttpServletRequest request
	// @GetMapping(value = "/idcheck")
	public String idCheck(Model m, String id) {
		// String id = request.getParameter("id");
		String dbid = "xman";
		if (dbid.equals(id)) {
			m.addAttribute("res", 1);
		} else {
			m.addAttribute("res", 0);
		}
		return "member/idchk";
	}

	// 이름: 테스형 , 제출형식: 댓글 스크린 샷
	// memberForm 입력된 파라미터를 모두 sysout으로 각각 콘솔에 출력하고
	// member/success.jsp에서는 이 데이트를 각각 EL로 출력하시오.
	// public String memberIn(????,HttpServletRequest request) {
	// return "member/success"
	// }
	@PostMapping("/memberInBack")
	public String memberIn(Model m, String id, String pwd, String uname, String[] chk) {
		System.out.println("id:" + id);
		System.out.println("pwd:" + pwd);
		System.out.println("uname:" + uname);
		for (String e : chk) {
			System.out.println(e);
		}
		m.addAttribute("id", id);
		m.addAttribute("uname", uname);
		return "member/success";
	}

	// DTO로 파라미터를 바인딩 시켜준다.
	// form or get
	// name="파라미터이름" == DTO의 property
	// @ModelAttribute 사용하면 모델이 수행 후 값을
	// m.addAttribute("vo", vo) 와 같다. 전송하는 것과 같다.
	@PostMapping("/memberInBack2")
	public String memberIn(Model m, @ModelAttribute(value = "vo") MemberDTO vo) {
		System.out.println("-----------------------");
		System.out.println("id:" + vo.getId());
		System.out.println("pwd:" + vo.getPwd());
		System.out.println("uname:" + vo.getUname());
		for (String e : vo.getChk()) {
			System.out.println(e);
		}
		vo.setMdate("2023-10-19");
		// m.addAttribute("vo", vo);
		// m.addAttribute("uname",vo.getUname());
		return "member/success2";
	}

	// @RequestParam 어노테이션은 파라미터를 검증하는 용도인데
	// Map<String, String> vo => @RequestParam을 지정한다.
	// Map일 경우에는 checkbox와 같이 배열타입을 따로 인자로 선언해서 가져오는 방법이 좋다.
	@PostMapping("/memberIn")
	public String memberIn(Model m, @RequestParam Map<String, Object> vo, String[] chk) {
		for (Map.Entry<String, Object> e : vo.entrySet()) {
			System.out.println(e.getKey() + ":" + e.getValue() + ":" + e.getClass());
		}
		for (String e : chk) {
			System.out.println(e);
		}
//		System.out.println("-----------------------");
//		System.out.println("id:"+vo.get("id"));
//		System.out.println("pwd:"+vo.get("pwd"));
//		System.out.println("uname:"+vo.get("uname"));
		vo.put("mdate", "2023-10-19");
		m.addAttribute("vo", vo);
		return "member/success2";
	}

	// GET방식 파라미터 처리 **** <중요> DTO로 받을 수 있다.
	// http://localhost/spring1019_mvc/joinSuccess?type=supermember&id=bigdaddy
	@GetMapping("/joinSuccess")
	public String joinSuccess(Model m, String type, String id) {
		String wcMsg = "환영합니다." + id + "님 , 등급 :" + type;
		m.addAttribute("wcMsg", wcMsg);
		return "member/success3";
	}

	// 파라미터 검증용 **** <중요>
	// @RequestParam(required = true) 해당속성의 파라미터를 검증 => HTTP 상태 400 => 잘못된 요청
	// @RequestParam(defaultValue = "guest")
	// http://localhost:8099/spring1019_mvc/joinSuccess2?type=xman (오류)
	// http://localhost:8099/spring1019_mvc/joinSuccess2?type=&id=bigdaddy => guest
	// http://localhost:8099/spring1019_mvc/joinSuccess2?type=&id=
	@GetMapping("/joinSuccess2")
	public String joinSuccess2(Model model, @RequestParam(defaultValue = "guest") String type,
			@RequestParam(required = true) String id) {
		String wcMsg = "환영합니다." + id + "님 , 등급 :" + type;
		model.addAttribute("wcMsg", wcMsg);
		return "member/success3";
	}

	// http://localhost:8099/spring1019_mvc/joinSuccess3?type=xman&idx=11v
	@GetMapping("/joinSuccess3")
	public String joinSuccess3(Model model, @RequestParam Map<String, String> param) {
		// GET방식으로 넘어온 파라미터를 키와 값으로 추출해서 출력하거나 가공하고 싶을 때 사용
		StringBuilder sb = new StringBuilder();
		sb.append("안녕하세요").append("\n");
		for (Map.Entry<String, String> e : param.entrySet()) {
			sb.append(e.getKey()).append(":");
			sb.append(e.getValue()).append("\n");
		}
		System.out.println(sb);
		System.out.println("--------------------");
		// 미리 정해두고 사용할 때
		String wcMsg = "환영합니다." + param.get("id") + "님 , 등급 :" + param.get("type");
		model.addAttribute("wcMsg", wcMsg);
		return "member/success3";
	}

	// <중요> : 블로그나 , Vue,React 단의 파라미터 값으로 전송 받을때도 자주 사용
	// @PathVariable -> path중에 {path} 값으로 받아서 파라미터로 인식 하겠다는 설정
	// http://localhost:8099/spring1019_mvc/path1/xman =? /{xman}
	@GetMapping("/path1/{m}")
	public String path1(Model model, @PathVariable String m) {
		System.out.println(m);
		model.addAttribute("wcMsg", m);
		return "member/success3";
	}

	// @PathVariable -> path중에 {path} 값으로 받아서 파라미터로 인식 하겠다는 설정
	// http://localhost:8099/spring1019_mvc/path2/mywork/seoul
	// addr로 파라미터를 받아서 선언한 변수 n으로 받겠다는 설정
	@GetMapping("/path2/{m}/{addr}")
	public String path1(Model model, @PathVariable String m, @PathVariable("addr") String n) {
		System.out.println(m);
		String msg = m + "/" + n;
		model.addAttribute("wcMsg", msg);
		return "member/success3";
	}

	

	// 테스형 : A팀
	// Ajax 최종테스트
	// 아이디값이 존재하면 1,아니면 0
	// <요구사항> : url:idcheck
	// 파라미터: id 는 반드시 존재해야 한다.
	// 더미 데이터 "xman","bigdaddy","postman" 아이디배열로 만들어서
	// 전송된 파라미터값이 아이디 배열에 존재하면 커스텀뷰에 1 반환하고 아니면 0을반환하는 메서드를 정의한 후
	// 실제 폼에서 테스트 하시오.
	@GetMapping("/idcheck")
	@ResponseBody
	public String idCheck(@RequestParam(required = true) String id) {
		String[] dbid = { "xman", "bigdaddy", "postman" };
		int res = 0;
		for (String e : dbid) {
			if (e.equals(id)) {
				res = 1;
				break;
			}
		}
		System.out.println("res=>" + res);
		return String.valueOf(res);
	}
}
