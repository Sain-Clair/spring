package kr.co.ictedu.mvc.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import kr.co.ictedu.mvc.dto.ChatVO;

@Component
//ws://호스트/chat 접속시
@ServerEndpoint("/chat")
public class ChatHandler {
	private final static String PATH = "E:\\ICT\\workspace2\\spring\\spring1025_AopLogin\\WebContent\\chat.txt";
//	E:\ICT\workspace2\spring\spring1025_AopLogin\WebContent\WEB-INF\views\chat\chatdemo1.jsp
	private static List<ChatVO> users = new LinkedList<ChatVO>();
	private Gson gson = new Gson();

	// 웹소켓의 세션을 받아서 ChatSession을 탐색하는 메서드를 정의한다.
	private ChatVO getSession(Session userSession) {
		System.out.println("1. userSession:" + userSession);
		Optional<ChatVO> data = users.stream().filter(x -> x.getSession() == userSession).findFirst();
		if (data.isPresent()) {
			return data.get(); // stream에 ChatVO 반환
		}
		// List<ChatVO>에 존재하지 않으면 null을 반환함.
		return null;
	}

	// 세션 만들기 : 새로운 세션이면 만들고 아니면 기존의 세션을 리턴 시킨다.
	private ChatVO createSession(ChatVO msg, Session userSession) {
		// 세션의 값 가져오기
		ChatVO session = getSession(userSession);
		if (session == null) {
			// 인스턴스 생성
			session = new ChatVO();
			// 세션 저장
			session.setSession(userSession);
			// user 리스트에 추가
			users.add(session);
		}
		// id 저장
		session.setId(msg.getId());
		// session 리턴
		return session;
	}

	// @OnOpen: WebSocket이 접속될 때 호출되는 함수에 사용되는 어노테이션 -> accept()
	// @OnMessage: 브라우저로부터 메시지가 오면 호출되는 함수 -> readLine()
	// @OnClose: WebSocket이 닫히면 호출되는 함수
	@OnOpen
	public void handleOpen(Session usersession) {
		System.out.println("WebSocket이 접속");
		System.out.println("id: " + usersession.getId());
	}

	@OnMessage
	public void handleMessage(Session userSession, String message) {
		// json type
		ChatVO msg = gson.fromJson(message, ChatVO.class);
		System.out.println("상태 표시: "+msg.getState());
		// status가 0이 되면 최초 접속, 1이 되면 일반 메세지 판단.
		// 프로토콜: 0 -> enter, 1 -> chat
		if(msg.getState() == 0) {
			createSession(msg, userSession);
			// File에 저장한 내용을 읽어오기
			try {
				userSession.getBasicRemote().sendText(readFile());
			} catch (IOException e) {
				// FIXME Auto-generated catch block
				e.printStackTrace();
			}
		}else if (msg.getState() == 1) {
			// 세션 확인 하기
			if(getSession(userSession) != null) {
				// 메세지를 전송
				sendMessageData(msg.getId(),msg.getValue());
				// 파일에 저장
				saveFile(msg.getId(), msg.getValue());
			}
		}
	}
	
	private void saveFile(String id, String message) {
		// 메세지 내용. 모든 사용자에게 같이 뿌려야 함 -->MyServerSocket
		String sendMessage = id + ":" + message + "\n";
		try (PrintWriter pw = new PrintWriter(new FileOutputStream(PATH),true);){
			pw.println(sendMessage);
		} catch (Exception e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendMessageData(String id, String message) {
		// 메세지 내용. 모든 사용자에게 같이 뿌려야 함 -->MyServerSocket
		String sendMessage = id + ":" + message + "\n";
		// 브로드 캐스팅
		for(ChatVO user : users) {
			// 메세지 전송
			try {
				user.getSession().getBasicRemote().sendText(sendMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String readFile() {
		File file = new File(PATH);
		if(!file.exists()) {
			return "";
		}
		try (BufferedReader rd = new BufferedReader(new FileReader(file));){
			StringBuilder sb = new StringBuilder();
			String msg = null;
			while((msg = rd.readLine())!= null) {
				sb.append(msg);
			}
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "깔깔";
	}
	
	@OnClose
	public void handleClose(Session userSession) {
		Optional<ChatVO> session = users.stream().filter(x -> x.getSession() == userSession).findFirst();
		if(session.isPresent()) {
			// List에서 해당 세션을 지운다.
			users.remove(session.get());
			System.out.println("userSize: " + users.size());
			if(users.size()==0) {
				File file = new File(PATH);
				file.delete();
			}
		}
	}
}
