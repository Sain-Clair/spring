package ex3.advice;

import org.springframework.beans.factory.annotation.Autowired;

import ex3.MyPublic;

public class TodayBeforeAdvice {
	
	@Autowired
	private MyPublic myPublic;
	
	public void TodayDate() {
		System.out.println("오늘의 날짜는: " + myPublic.todayMethod());
		
	}
}
