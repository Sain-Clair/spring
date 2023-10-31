package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;

// 인터페이스 구성 : 추상메서드, 상수, default method, static method
// 이 인터페이스 사용 주체  = 서블릿
// 인터페이스 사용 이유: 결합도 ↓, 다형성
public interface Action {
		public ActionForward excute(HttpServletRequest request, HttpServletResponse response)throws IOException;
}
