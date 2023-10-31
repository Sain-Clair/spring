package ex1;

public class SamsungTV implements TV{
//	property를 등록
	private String msg;
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public void powerOn() {
		System.out.println(msg+"SamsungTV---전원켠다");
	}
	@Override
	public void powerOff() {
		System.out.println(msg+"SamsungTV---전원끔");		
	}
	@Override 
	public void volumeUP() {
		System.out.println(msg+"SamsungTV---볼륨업");		
	}
	@Override
	public void volumeDown() {
		System.out.println(msg+"SamsungTV---볼륨다운"); 		
	}
}
