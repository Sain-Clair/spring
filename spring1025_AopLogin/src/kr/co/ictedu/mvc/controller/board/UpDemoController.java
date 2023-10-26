package kr.co.ictedu.mvc.controller.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ictedu.mvc.dao.UpBoardDaoInter;
import kr.co.ictedu.mvc.dto.BoardCommVO;
import kr.co.ictedu.mvc.dto.BoardVO;
import kr.co.ictedu.mvc.dto.PageVO;


@Controller
@RequestMapping("/upBoard")
public class UpDemoController {

	@Autowired
	private UpBoardDaoInter upBoardDaoInter;
	@Autowired
	private PageVO pageVO;
	
	@PostMapping("/bcominsert")
	public String bcominsert (BoardCommVO vo) {
		upBoardDaoInter.addComm(vo);
		return "redirect:upBoardDetail?num="+vo.getUcode();
	}
	@GetMapping("/upform")
	public String upform() {
		return "updemo/upform";
	}
	
	@GetMapping("/upBoardDetail")
	public String upBoardDetail(Model m,
			@RequestParam(required = true)int num, // 글번호
			@RequestParam(defaultValue = "detail") String type, // hit 값 조절.
			@RequestParam Map<String,String> paramMap) { // 댓글 페이징에 대한 begin, end 정보를 담음. (기본 담겨있음. searchType, searcValue, cPage)
		
		// 댓글 작성으로 넘어온 경우가 아닐경우(게시물 상세보기로 넘어온 경우), 조회수 갱신하기
	    if (!type.equals("comm")) {
	        upBoardDaoInter.upboardHit(num);
	    } 
		
		String cPage = paramMap.get("cPage");
		pageVO.setTotalRecord(upBoardDaoInter.cmtotalCnt(num)); //
		
		int totalRecord = pageVO.getTotalRecord();
		pageVO.setTotalPage((int) Math.ceil(totalRecord / (double) pageVO.getNumPerPage()));
		pageVO.setTotalBlock((int) Math.ceil((double) pageVO.getTotalPage() / pageVO.getPagePerBlock()));
		
		if (cPage != null) {
			pageVO.setNowPage(Integer.parseInt(cPage));
		} else {
			pageVO.setNowPage(1);
		}
		pageVO.setBeginPerPage((pageVO.getNowPage()-1) * pageVO.getNumPerPage() + 1);
		pageVO.setEndPerPage((pageVO.getBeginPerPage()-1) + pageVO.getNumPerPage());
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("begin", String.valueOf(pageVO.getBeginPerPage()));
		map.put("end", String.valueOf(pageVO.getEndPerPage()));
		
		map.put("ucode",String.valueOf(num));
		System.out.println(map.toString());
		// map에 paramMap 합치기
		map.putAll(paramMap); // cPage 필요
		
		int startPage = (int) ((pageVO.getNowPage()-1) / pageVO.getPagePerBlock()) * pageVO.getPagePerBlock() + 1;
		int endPage = startPage + pageVO.getPagePerBlock()-1;
		if(endPage > pageVO.getTotalPage()) {
			endPage = pageVO.getTotalPage();
		}
		
		System.out.println("begin: "+String.valueOf(pageVO.getBeginPerPage()));
		
		List<BoardCommVO> listComm = upBoardDaoInter.listCommBoard(map);
		
		m.addAttribute("v",upBoardDaoInter.upboardDetail(num));
		
		
		m.addAttribute("startPage", startPage); // 블록 시작페이지
		m.addAttribute("endPage", endPage);// 블록 종료페이지
		m.addAttribute("page", pageVO); // nowPage, pagePerBlock,totalPage
		m.addAttribute("listComm",listComm);
		m.addAttribute("num",num);
		
		return "updemo/upDetail";
	}
	
	
	
	@PostMapping("/upboardmodify")
	public String upboardModify (int num, Model m) {
		m.addAttribute("v", upBoardDaoInter.upboardDetail(num));
		return "updemo/upModify";
	}
	@PostMapping("/upboardUpdate")
	public String upBoardUpdate(BoardVO vo, HttpServletRequest request) {

		MultipartFile mf = vo.getMfile();
		String oriFn = mf.getOriginalFilename();
		System.out.println("oriFn : " + oriFn);

		// 경로 테스트 : 이미지가 저장할 경로
		String img_path = "resources\\imgfile";

		// 이클립스 상에 저장할 이미지 경로
		String r_path = request.getSession().getServletContext().getRealPath("/");
		System.out.println("r_path : " + r_path);

		long size = mf.getSize();
		String contentType = mf.getContentType();

		System.out.println("파일 크기" + size);
		System.out.println("파일의 type : " + contentType);

		// 메모리상(임시저장장소)에 파일을 우리가 설정한 경로에 복사 하겠다.
		// 이미지가 저장될 경로 만들기
		StringBuffer path = new StringBuffer();
		path.append(r_path).append(img_path).append("\\");
		path.append(oriFn);
		System.out.println("FullPath : " + path);

		// 추상경로(이미지를 저장할 경로) File 객체로 생성
		// File 클래스의 메서드는 복습을 반드시 할것 *****
		File f = new File(path.toString());

		// 임시 메모리에 담긴 즉 업로드한 파일의 값 -> File 클래스의 경로로 복사
		try {
			mf.transferTo(f);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		// Dao 데이터 입력처리
		vo.setImgn(oriFn);

		upBoardDaoInter.upboardUpdate(vo);
		return "redirect:upBoardDetail?num=" + vo.getNum();
	}
	@PostMapping("/upboardDelete")
	public String upBoardDelete(int num) {
		upBoardDaoInter.upboardDelete(num);
		return "redirect:upList";
	}
	
	
	
	// uploadpro
	@PostMapping("/uploadpro")
	public String uploadFile(Model m, BoardVO vo, HttpServletRequest request) { // Model m.. forward 때 사용.
		// 파라미터 테스트
		System.out.println("Title: " + vo.getTitle());
		// MultiparFile 객체에서 이름 확인
		MultipartFile mf = vo.getMfile();
		String oriFn = mf.getOriginalFilename();
		System.out.println("oriFn:  " + oriFn);

		// 경로 테스트 : 이미지가 저장될 경로
		String img_path = "resources\\imgfile";

		// 이클립스 상에 저장할 이미지 경로
		String r_path = request.getSession().getServletContext().getRealPath("/");
		System.out.println("r_path: " + r_path);

		// 이미지 사이즈 및 contentType 확인
		long size = mf.getSize();
		String contentType = mf.getContentType();
		System.out.println("파일 크기: " + size);
		System.out.println("파일의 type : " + contentType);

		// 메모리상(임시 저장소)에 파일을 우리가 설정한 경로에 복사 하겠다.
		// 이미지가 저장될 경로 만들기
		StringBuffer path = new StringBuffer();
		path.append(r_path).append(img_path).append("\\");
		path.append(oriFn);
		System.out.println("FullPath: " + path);

		// _________________________________↑ 메모리에서만 존재
		// _________________________________↓ DB 저장

		// 추상경로(이미지를 저장할 경로 ) File 객체로 생성
		// File 클래스의 메서드는 복습을 반드시 할 것 ★★★★★
		File f = new File(path.toString());
		// 임시 메모리에 담긴 즉 업로드한 파일의 값 -> File 클래스의 경로로 복사
		try {
			mf.transferTo(f);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		// Dao 데이터 입력처리
		vo.setImgn(oriFn);
		upBoardDaoInter.upboardAdd(vo);

		return "redirect:upList";
	}
	@RequestMapping("/upList") // ???
	public String upBoardList(Model model, @RequestParam Map<String, String> paramMap) {
		System.out.println("파라미터 출력: ");
		System.out.println("검색시, 링크시 넘어오는 파라미터 출력: ");
		String cPage = paramMap.get("cPage");
		
		System.out.println("cPage: " + cPage);
		System.out.println("searchType: " + paramMap.get("searchType"));
		System.out.println("searchValue: " + paramMap.get("searchValue"));
		System.out.println("*****************************");
		
		// 1. totalRecord
		pageVO.setTotalRecord(upBoardDaoInter.getTotal(paramMap));
		int totalRecord = pageVO.getTotalRecord();
		System.out.println("1. TotalRecord:" + totalRecord);
		// 2. totalPage
		// totalRecord/numPerPage
		// =(int)Math.ceil(totalRecord/(double)numPerPage);
		// int totalPage
		pageVO.setTotalPage((int) Math.ceil(totalRecord / (double) pageVO.getNumPerPage()));
		System.out.println("2. totalPage: " + pageVO.getTotalPage());
		/*
		 * 3. totalBlock // 전체 블록 구하기 => 전체 페이지(totalPage)/보여줄 블록수(pagePerBlock)
		 * totalBlock = 6/5; totalBlock = (int) Math.ceil((double) totalPage /
		 * pagePerBlock); System.out.println("3. totalBlock :" + totalBlock);
		 */
		pageVO.setTotalBlock((int) Math.ceil((double) pageVO.getTotalPage() / pageVO.getPagePerBlock()));
		System.out.println("3. totalBlock: " + pageVO.getTotalBlock());
		// 현재 페이지를 요청할 때 파라미터 로 현재 페이지 값을 받는다.
		// 한 페이지에 10개 씩.
		if (cPage != null) {
			pageVO.setNowPage(Integer.parseInt(cPage));
		} else {
			pageVO.setNowPage(1);
		}
		System.out.println("4. nowPage : " + pageVO.getNowPage());
		pageVO.setBeginPerPage((pageVO.getNowPage()-1) * pageVO.getNumPerPage() + 1);
		pageVO.setEndPerPage((pageVO.getBeginPerPage()-1) + pageVO.getNumPerPage());
		System.out.println("5. beginPerPage = " + pageVO.getBeginPerPage());
		System.out.println("5. endPerPage = " + pageVO.getEndPerPage());
		
		// 페이징 테스트
		Map<String, String> map = new HashMap<String, String>();
		map.put("begin", String.valueOf(pageVO.getBeginPerPage()));
		map.put("end", String.valueOf(pageVO.getEndPerPage()));

		// map에 paramMap 합치기
		map.putAll(paramMap);
		// 검수
		System.out.println("===========MAP==========");
		for(Map.Entry<String, String> e: map.entrySet()) {
			System.out.println(e.getKey()+ ","+ e.getValue());
		}
		List<BoardVO> list = upBoardDaoInter.upboardList(map);
		System.out.println("Size: " + list.size());
		
		int startPage = (int) ((pageVO.getNowPage()-1) / pageVO.getPagePerBlock()) * pageVO.getPagePerBlock() + 1;
		int endPage = startPage + pageVO.getPagePerBlock()-1;
		if(endPage > pageVO.getTotalPage()) {
			endPage = pageVO.getTotalPage();
		}
				
		System.out.println("6. startPage = "+ startPage);
		System.out.println("6. endPage = " + endPage);
		System.out.println("_____________________________");
		
		model.addAttribute("searchType", map.get("searchType"));
		model.addAttribute("searchValue", map.get("searchValue"));
		model.addAttribute("startPage", startPage); // 블록에 시작페이지 값
		model.addAttribute("endPage", endPage);// 블록에 종료 페이지 값
		model.addAttribute("page", pageVO); // nowPage, pagePerBlock,totalPage
		model.addAttribute("list",list);
		
		return "updemo/upList";
	}
	

}
