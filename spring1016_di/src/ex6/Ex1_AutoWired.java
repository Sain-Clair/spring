package ex6;

import org.springframework.beans.factory.annotation.Autowired;

public class Ex1_AutoWired {
	@Autowired
	private String msg;
//	public void setMsg(String msg) {
//		this.msg = msg;
//	}
	public String getMessage() {
		StringBuffer sb = new StringBuffer();
		sb.append("<p style='color:blue'>");
		sb.append(msg);
		sb.append("<p>");
		return sb.toString();
	}
	
}
