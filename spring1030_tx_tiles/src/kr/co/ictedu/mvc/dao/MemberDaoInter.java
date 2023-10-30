package kr.co.ictedu.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.co.ictedu.mvc.dto.MemberVO;
import kr.co.ictedu.mvc.dto.MyLoginLoggerVO;

public interface MemberDaoInter {
	public void add(MemberVO vo);
	public int idCheck(String id);
	public MemberVO loginCheck(MemberVO vo);
	public MemberVO myPage(String id);
	public List<MemberVO> memList(Map<String, String> map);
	public int getCnt();
	// 로그인 로깅
	public void addLoginLogging(MyLoginLoggerVO vo);
	public List<MyLoginLoggerVO> logList(String idn);
	public int MaxloginNum(String idn);
}
