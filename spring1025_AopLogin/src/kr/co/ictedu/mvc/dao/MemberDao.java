package kr.co.ictedu.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ictedu.mvc.dto.MemberVO;

@Repository
public class MemberDao implements MemberDaoInter {
	
	@Autowired
	private SqlSessionTemplate ss;
	
	
	
	@Override
	public void add(MemberVO vo) {
		
		ss.insert("mem.add",vo);
	}

	@Override
	public int idCheck(String id) {
		// FIXME Auto-generated method stub
		return ss.selectOne("mem.idcheck",id);
	}
	
	// <select id="logincheck" parameterType="mvo" resultType="mvo">
	@Override
	public MemberVO loginCheck(MemberVO vo) {
		return ss.selectOne("mem.logincheck",vo);
	}

	@Override
	public MemberVO myPage(String id) {
		// FIXME Auto-generated method stub
		return ss.selectOne("mem.mypage", id);
	}
	
	/* ↓ 알아서 */
	@Override
	public List<MemberVO> memList(Map<String, String> map) {
		return ss.selectList("");
	}

	@Override
	public int getCnt() {
		// FIXME Auto-generated method stub
		return 0;
	}
	

}
