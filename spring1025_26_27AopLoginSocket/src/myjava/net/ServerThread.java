package myjava.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ServerThread extends Thread{
	private Socket socket; // 연결된 각각의 소켓의 정보. ex) a클라이언트.. b클라이언트.. 스레드 관리를 소켓이 한다
	private MyServerSocket server; // 브로드 캐스팅
	private PrintWriter pw;
	private BufferedReader in;
	private String reip;
	private Map<String, String> map;
	
	public ServerThread(Socket socket, MyServerSocket server) {
		this.socket = socket;
		this.server= server;
		reip = socket.getInetAddress().getHostAddress();
		map = new HashMap<String, String>();
		map.put("누구?", "뚱인데요?");
		map.put("누구세요", "뚱인데요?");
		map.put("누구시냐고요", "뚱인데요?");
		map.put("누구시냐고요", "뚱인데요?");
		map.put("누구시냐고요", "뚱인데요?");
		try {
			pw = new PrintWriter(socket.getOutputStream(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (true) { // 여기서 한명의 사람과 대화를 하기 때문에 새로운 사람을 (line:33 blocking)  받지 못함.
				String msg = in.readLine(); // blocking
				System.out.println("클라이언트 메세지: " + msg);
				transMsg(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void transMsg(String msg) { // 브로드 캐스팅(전파)되는 메시지
		// 심심이
		// String serMsg = map.get(msg);
		// ex) chat/하이
		//	   draw/color/x/y
		StringTokenizer stn = new StringTokenizer(msg,"/");
		String str1 = stn.nextToken();
		String str2 = stn.nextToken();
		String str3 = stn.nextToken();
		String str4 = reip;
		server.sendMsg(str1,str2,str3,str4,str4, this);
	}

	public PrintWriter getPw() {
		return pw;
	}

}
