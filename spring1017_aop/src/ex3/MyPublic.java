package ex3;

import java.text.SimpleDateFormat;
import java.util.Date;

// TodayBeforeAdvice에서 MyPublic를 DI를 받아서 Advice에서 적용을 시킨다.
// ex1.DaoIMple 에 정의된 모든 메서드에 선행작업으로 공통관심로직이 적용되도록 수행하시오.
public class MyPublic {
	public String todayMethod() {
		SimpleDateFormat f= new SimpleDateFormat("yyyy-MM-dd");		
		return f.format(new Date());
	}
}
