package kr.co.ictedu.admin.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ictedu.mvc.dto.SurveyContentVO;
import kr.co.ictedu.mvc.dto.SurveyVO;

@Repository
public class SurveyDao {
	@Autowired
	private SqlSessionTemplate ss;
	
	public void addSurvey(SurveyVO vo) {
		ss.insert("survey.add", vo);
	}
	public void addSurveyContent(List<SurveyContentVO> list) {
		ss.insert("survey.addcontent", list);
	}
	public List<SurveyVO> listSurvey(){
		return ss.selectList("survey.listSurvey");
	}
	public SurveyVO adminDetail(int num) {
		SurveyVO vo = ss.selectOne("survey.adminDetail",num);
		// 검수
		List<SurveyContentVO> list = vo.getSubvey();
		System.out.println("SurveyContentVO Size: " + list.size());
		return vo;
	}
}
