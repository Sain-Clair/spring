package kr.co.ictedu.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.ictedu.mvc.dto.BoardCommVO;
import kr.co.ictedu.mvc.dto.BoardVO;

@Repository
public class UpBoardDao implements UpBoardDaoInter{
	@Autowired
	private SqlSessionTemplate ss;
	private UpBoardDaoInter UpBoardDaoInter;

	@Override
	public void upboardAdd(BoardVO vo) {
		ss.insert("upboard.add",vo);
		// namespace / id
	}

	@Override
	public int getTotal(Map<String, String >map) {
		
		return ss.selectOne("upboard.totalCount",map);
	}
	// 수정
	@Override
	public void upboardUpdate(BoardVO vo) {
		ss.update("upboard.update",vo);
	}
	// 삭제
	@Override
	public void upboardDelete(int num) {
		ss.delete("upboard.delete", num);
	}
	// 디테일
	@Override
	public BoardVO upboardDetail(int num) {
		return ss.selectOne("upboard.detail",num);
	}

	@Override
	public void addComm(BoardCommVO vo) {
		ss.update("upboard.addcomm",vo);
	}

	@Override
	public void upboardHit(int num) {
		ss.update("upboard.updateHit", num);
	}
	
	@Override
	public List<BoardVO> upboardList(Map<String, String> map) {
		return ss.selectList("upboard.list",map);
	}
	// 댓글입력 처리
	// form의 action, method를 확인해서 작성
	@PostMapping("/bcominsert")
	public String bcominsert(BoardCommVO cvo, Model model) {
		UpBoardDaoInter.addComm(cvo);
		return "redirect:upboardDetail?num="+ cvo.getUcode()+"&type=comm#comm";
	}
	
	@Override
	public List<BoardCommVO> listCommBoard(Map<String, String> map) {
		for(String key : map.keySet()) {
			System.out.println("tereser:" + key + ":::::"+map.get(key));
		}
		return ss.selectList("upboard.listComm", map);
	}

	@Override
	public int cmtotalCnt(int ucode) {
		return ss.selectOne("upboard.cmtotalCnt", ucode);
	}
	
	
	
}
