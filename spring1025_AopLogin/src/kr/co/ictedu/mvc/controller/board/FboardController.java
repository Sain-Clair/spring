package kr.co.ictedu.mvc.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.ictedu.mvc.dao.FBoardDaoInter;
import kr.co.ictedu.mvc.dto.FboardDTO;
 
@Controller
@RequestMapping("/fboard")
public class FboardController {
	
	@Autowired
	private FBoardDaoInter fBoardDaoInter;
	
	// form
	@GetMapping("/fBoardWrite") // a href
	public String tboardForm() {
		return "/fboard/write";
	}
	// 메서드 이름: fboardInsert
	// sysout으로 입력값 출력
	// 반환형: void
	// 풀이) 
	@PostMapping("/fboardInsert") // action
	public String fboardInsert(FboardDTO vo) {
		System.out.println(vo.getContent());
		System.out.println(vo.getDate());
		System.out.println(vo.getFdate());
		System.out.println(vo.getHit());
		System.out.println(vo.getNum());
		System.out.println(vo.getReip());
		System.out.println(vo.getSubject());
		System.out.println(vo.getWriter());
		
		// DB에 데이터 삽입
		fBoardDaoInter.addFBoard(vo);
		
		// 리다이렉트로 다시 컨트롤러에게 요청을 하면서, 요청에 따른 핸들러 매핑이 진행이 됨.
		return "redirect:fboardList";
	}
	@GetMapping("/fboardList")
	public String fboardList(Model m) {
		List<FboardDTO> list = fBoardDaoInter.listFBoard();
		m.addAttribute("list",list);				
		return "/fboard/list";
	}
	// list에서 상세보기 링크를 클릭하면 hit 호출되고 +1 업데이트 한 후
	// redirect:fboardDetail 사용해서 num을 파라미터로 전송한다.
	//hit
	@GetMapping("/fboardHit")
	public String fboardHit(int num) {
		fBoardDaoInter.updateHit(num);
		return"redirect:fboardDetail?num="+num;
	}
	// detail - select * from fboard where num = 1;
	@GetMapping("/fboardDetail")
	public String fboardDetail(Model model, int num) {
		FboardDTO v = fBoardDaoInter.detailFboard(num);
		model.addAttribute("v",v);
		return"/fboard/info";
	}
	@GetMapping("/fboardModify")
	public String fboardModify(Model m, int num) {
		FboardDTO v = fBoardDaoInter.detailFboard(num);
		m.addAttribute("v", v);
		return "/fboard/modify";
	}
	@PostMapping("/fboardUpdate")
	public String fboardUpdate(FboardDTO vo) {
		fBoardDaoInter.updateFboard(vo);
		return "redirect:fboardDetail?num="+vo.getNum();
	}
	
	@PostMapping("/fboardDelete")
	public String fboardDelete(int num) {
		fBoardDaoInter.deleteFboard(num);
		return "redirect:fboardList";
	}
	
	
}
