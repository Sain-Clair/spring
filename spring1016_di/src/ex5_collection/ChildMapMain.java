package ex5_collection;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ChildMapMain {
	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext("ex5_collection/map_child.xml");
		Ex2_Map ref = ctx.getBean("map2", Ex2_Map.class);
		Map<String, String> maps = ref.getMap();
		System.out.println(maps);
//		for문을 사용해서 key=>, val=>
//		for(Map.Entry<String, String> e: maps.entrySet()) {			
//			System.out.println("key->"+e.getKey()+", val=>" + e.getValue());
//		
//		}
		//lambda 식
		maps.forEach((k,v) -> System.out.println("key ->" + k + "value -> " + v));
		((AbstractApplicationContext)ctx).close(); 
	} 
}
