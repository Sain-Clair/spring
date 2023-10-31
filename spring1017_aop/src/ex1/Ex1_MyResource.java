package ex1;
// DI 대상이 되는 객체
public class Ex1_MyResource {
	
	private String conts;
	
	public void setConts(String conts) {
		this.conts = conts;
	}
	public String resource1() {
		return conts;
	}
//	↑ 이걸  주입 받을 객체를 생성해야 함
}
