package ex1;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

// Ex1_MyResource를 주입 받아서 사용할 객체
public class Ex1_Resource {
//	java에서 제공해주는 기본 라이브러리
//	name alias를 사용해서 불러와서 자동 빈 묶기를 함 ★★★★★
//	@Resource, @Qulifier 적용한 byType일 경우 수식어를 따른다.
//	그렇지만 @Resource(name= "resn1"), @Qualifier("bb") 일 경우에는 Alias를 따른다.
//	원리는 이렇지만 보통 @Resource(name= "resn1") 만으로도 충분하기 때문에 이렇게 사용하는 것이 일반적이다.
//	적용 우선순위 ID > NAME > @Qulifier
	@Resource(name= "resn1")
	@Qualifier("bb")
//	@Autowired
	private Ex1_MyResource res;
// bean 실행 시 __________________
//	public void setRes(Ex1_MyResource res) {
//		this.res = res;
//	}
//	_______________________ 여기 까지 실행, AutoWired 하면 필요 없음.
	public Ex1_MyResource getRes() {
		return res;
	}
	
}
