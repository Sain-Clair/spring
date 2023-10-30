package kr.co.ictedu.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.ictedu.admin.dao.SurveyDao;
import kr.co.ictedu.mvc.dto.SurveyContentVO;
import kr.co.ictedu.mvc.dto.SurveyVO;

@Service
public class SurveyService {
	//Dao를 단위 처리하기위한 서비스
	@Autowired
	private SurveyDao surveyDao;
	// AOP - 보안, 로깅 ,트랜잭션
	// Transaciton 처리를 하기 위해서
	// 빈에서 설정<tx.annotation-driven>, DataSourceTransactionManager에 의해서 Aop 적용
	@Transactional // xml에 등록만 해주면 에러 날 시 DB에 데이터 안들어감.
	public void addSurvey(SurveyVO vo, List<SurveyContentVO> list) {
		// 단위 처리 적용!
		surveyDao.addSurvey(vo); // commit X
		surveyDao.addSurveyContent(list); // commit X
		// commit
		
	}
	public List<SurveyVO> listSurvey(){
		return surveyDao.listSurvey();
	}
	public SurveyVO adminDetail(int num) {
		return surveyDao.adminDetail(num);
	}
	
	
}
