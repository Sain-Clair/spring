package kr.co.ictedu.mvc.controller.rest;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ictedu.mvc.dao.MemberDaoInter;
import kr.co.ictedu.mvc.dto.MyLoginLoggerVO;

@RestController
public class MyPageRestController {
	@Autowired
	private MemberDaoInter dao;

	@GetMapping(value = "/mypageRest", produces = "application/json; charset=utf-8")
	public Map<String, Object> myPage(HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		String id = (String) session.getAttribute("sessionID");
		List<MyLoginLoggerVO> list = dao.logList(id);
		int max = dao.MaxloginNum(id);
		map.put("max", max);
		map.put("list", list);
		return map;
	}
}
