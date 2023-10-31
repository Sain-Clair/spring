package ex1;
// 의존 관계가 형성된 2객체 생성
// A에게 B의 주소를 전달 후, 목표 메서드(A에 있는 printUseB()) 를 호출해서 
// 완료
public class MyFactoryMain {
	public static void main(String[] args) {
		MyTestA refA = new MyTestA();
//		ResourceB refB = new ResourceB();
//		refA.setB(refB);
//		의존관계 전달
		refA.setB(new ResourceB());
		System.out.println(refA.prutUseB());
		
	}

}