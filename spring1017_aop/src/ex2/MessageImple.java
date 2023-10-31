package ex2;

import java.util.LinkedList;

//Advice를 적용할  JoinPoint를 가질 수 있는 타켓 객체
public class MessageImple implements Message{
	private String message;
	public MessageImple() {
		message = "안녕하세요. 첫 AOP입니다.";
	}
	@Override
	public void print() {
		long start = System.currentTimeMillis();
		LinkedList<String> ar2 = new LinkedList<String>();
		for(int i=0; i< 100000; i++) {
			if(i % 5 == 0) {
				ar2.add("ict");
			}else {
				ar2.add("spring aop"+i);
			}
		}
		System.out.println("LinkedList 에 들어간 size:" + ar2.size());
		for (String e : ar2) {
			String msg = e;
			if (e.equals("ict")) {
				System.out.println(msg);
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("소요시간 :" + (end - start));
	}

	@Override
	public String printTest() {
		long start = System.currentTimeMillis();
		for(int i=0; i<10;i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("소요시간 :" + (end - start));
		return "헬로";
	}
	@Override
	public String message() {
		return message;
	}
	@Override
	public void test() {
		
	}
	@Override
	public void test2() {
		System.out.print("2");
	}

	@Override
	public void test3(String msg) {
		System.out.print("3");
	}

}