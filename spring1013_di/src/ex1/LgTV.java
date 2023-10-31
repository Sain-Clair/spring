package ex1;

public class LgTV implements TV{
//	property를 등록
	private String msg;
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public void powerOn() {
		System.out.println(msg+"LgTV---전원켠다");
	}
	@Override
	public void powerOff() {
		System.out.println(msg+"LgTV---전원끔");		
	}
	@Override
	public void volumeUP() {
		System.out.println(msg+"LgTV---볼륨업");		
	}
	@Override
	public void volumeDown() {
		System.out.println(msg+"LgTV---볼륨다운"); 		
	}
}
