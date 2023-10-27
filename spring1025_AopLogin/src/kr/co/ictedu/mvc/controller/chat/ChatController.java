package kr.co.ictedu.mvc.controller.chat;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
// request => chatdemo/chat
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/chatdemo")
public class ChatController {
	@RequestMapping("/chat")
	public ModelAndView viewChattingPage(HttpSession session) {
		ModelAndView mav =  new ModelAndView("chat/chatdemo1");
		if(session.getAttribute("sessionID") == null) {
			mav.setViewName("error/paramException");
			mav.addObject("emsg", "로그인 실패입니다.");
		}else {
			String img = "";
			if(session.getAttribute("sessionID").equals("bigdaddy")) {
				img = "mio.png";
			}else {
				img="dog1.png";
			}
			mav.addObject("cimg",img);
			mav.addObject("ssid",session.getAttribute("sessionName"));
		}
		return mav;
	}
}
