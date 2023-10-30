package kr.co.ictedu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternDemo1 {
	public static void main(String[] args) {
//		Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36
//
//		Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36 Edg/118.0.2088.61
//
//
//		--아이폰
//		Mozilla/5.0 (iPhone; CPU iPhone OS 17_0_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0.1 Mobile/15E148 Safari/604.1
//
//
//		-ipad-safari 
//		Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.3 Safari/605.1.15
//
//		-ipad-kakaotalk
//		Mozilla/5.0 (iPad; CPU OS 16_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 KAKAOTALK 10.2.8
//
//		Mozilla/5.0 (Linux; Android 10; SM-N960N Build/QP1A.190711.020; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/118.0.0.0 Mobile Safari/537.36;KAKAOTALK 2610380
//
//		Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Mobile Safari/537.36
		
		String userAgent ="";
		Pattern mp = Pattern.compile("(Mobile|Android|iPhone|Macintosh)");
		Matcher mc =mp.matcher(userAgent);
		boolean res=  mc.find();
		System.out.println(res);
		if(res) {
			System.out.println("Mobile");
		}else {
			System.out.println("PC");
		}
		
		Pattern mp1 = Pattern.compile("(Window NT [\\d.]+|Android [\\d.] +|iPhone)");
		Matcher mc1 =mp1.matcher(userAgent);
		
		if(mc1.find()) {
			String device = mc1.group();
			System.out.println(device);
		}
	}

}
