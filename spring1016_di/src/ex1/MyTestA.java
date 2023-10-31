package ex1;

// DI: 객체 의존성 주입이다.
public class MyTestA {
	// 멤버필드를 받을 객체를 선언!
	private ResourceB b;

	public MyTestA() {
		// has a 관계, 결합도가 너무 높다.
		// b = new ResourceB();
		System.out.println("MyTestA 생성");
	}
	// 외부에서 생성한 후에 그 주소를 주입 받는다.
	// <property name = "b" ...
	public void setB(ResourceB b) {
		this.b = b;
	}
	public String prutUseB() {
		StringBuilder sb = new StringBuilder();
		sb.append("B에서 반환 받은 값:" + (b.res() * 2)).append("\n");
		sb.append("사용자 이름:").append(b.getUname());
		return sb.toString();
	}
}
