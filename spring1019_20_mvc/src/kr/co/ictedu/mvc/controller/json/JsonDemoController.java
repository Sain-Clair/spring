package kr.co.ictedu.mvc.controller.json;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.ictedu.mvc.dto.BoardVO;

@Controller
public class JsonDemoController {
	private List<Map<String,String>> useList;
	public JsonDemoController() {
		useList = new ArrayList<Map<String,String>>();
	}
	@GetMapping("/blog2/{bnum}/{bid}")
	// 커스텀 뷰를 만들어 놓고 반환되는 내용을 알아서 출력, 만약에 반환값이 Object 일경우 Json타입으로 제공한다.
	@ResponseBody
	public String myBLog(@PathVariable Integer bnum, @PathVariable String bid) {
		bnum = bnum - 10;
		String msg = "bnum:" + bnum + ", bid: " + bid;
		System.out.println(msg);
		return msg;
	}
	// http://localhost/spring1019_mvc/board/01/ict
	// jackson-databind 추가
	@GetMapping("/blog/{bnum}/{bid}")
	@ResponseBody
	public BoardVO myBLog2(@PathVariable Integer bnum, @PathVariable String bid) {
		BoardVO vo = new BoardVO();
		vo.setNum(bnum);
        vo.setTitle("ㅎㅎㅎ" + bnum);
        vo.setWriter(bid);
        vo.setReip("192.168.0.1");
        vo.setContent("ㅎㅎ ㅈㅅ");
        vo.setBdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return vo;
	}
	@GetMapping("/boardJsonList")
	@ResponseBody
	public List<BoardVO> myBlog2(){
		System.out.println("boardJsonList 시작");
		List<BoardVO> list = new ArrayList<BoardVO>();
		// 임시 데이터 만들기
		for (int i = 0; i < 10; i++) {
			BoardVO vo = new BoardVO();
			vo.setNum(i+1);
			vo.setTitle("오늘은 왠지 ㅎ" + i);
			vo.setContent("내용1");
			vo.setReip("123.123.1.123");
			vo.setWriter("홍길동");
			vo.setBdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			list.add(vo);
		}
		return list;
	}
	// Ajax를 사용한 json 데이터 핸들링 form ★★★★
	@GetMapping("/ajaxBoard")
	public String ajaxDemoBoard() {
		
		return "/board/ajaxBoard";
	}
	
	// ★★★★★
	////RequestParamDemo : Ajax에 의해서 json 값으로 파라미터를 처리하기 위한 방법 소개
	// @ ResponseBody -> 모델이 수행 후 json 데이터 보낼 때
	// @ RequestBody -> 요청의 파라미터가 json Object 일 때
	@ResponseBody
	@PostMapping("/RequestParamDemo")
	public List<Map<String, String>> requestBodyDemo(@RequestBody Map<String, String > param){
		System.out.println("Test:" + param);
		// 클라이언트에서 Ajax로 전송된 데이터를 누적시킴.
		useList.add(param);
		System.out.println(useList.size()+"개 누적!");
		return useList;
	}
	
	
	
	
}
