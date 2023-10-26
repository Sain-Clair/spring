package kr.co.ictedu.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.co.ictedu.mvc.dto.BoardCommVO;
import kr.co.ictedu.mvc.dto.BoardVO;
// 구현 클래스 : UpBoardDao 정의하기
public interface UpBoardDaoInter {
	public void upboardAdd(BoardVO vo);
	public List<BoardVO> upboardList(Map<String,String> map);
	public int getTotal(Map<String, String> map);

	// 상세보기, 수정, 삭제 메소드
	public void upboardUpdate(BoardVO vo);
	public void upboardDelete(int num);
	public BoardVO upboardDetail(int num);

	//추가
	public void upboardHit(int num); // hit

	// 댓글 추가
	public List<BoardCommVO> listCommBoard(Map<String, String> map);
	public void addComm(BoardCommVO vo);
	public int cmtotalCnt(int num);
	
}
