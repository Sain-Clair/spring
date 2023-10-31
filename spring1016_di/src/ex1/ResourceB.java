package ex1;
// 자원을 다루는 객체, 예 ResourceB - Dao
public class ResourceB {
	private String uname;
	public ResourceB() {
		System.out.println("Resource가 생성이 됨!");
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int res() { // 특정 수를 돌려주는 메서드 
		return 100000;
	}
}
