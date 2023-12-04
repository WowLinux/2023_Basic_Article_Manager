package com.koreaIT.java.BAM;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static  String getNowDateStr() {        
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy년 MM월 dd일");
		Date now = new Date();         
//		String nowTime1 = sdf1.format(now);        
//		String nowTime2 = sdf2.format(now);         
//		System.out.println(nowTime1);        
//		System.out.println(nowTime2); 생성자는 클래스명과 매서드명이 동일하다.
//      일반적인 매서드는 접근제어자(Public/Private)+Static+리턴타입+매서드명+()+{}	
//      가 나와야 하는데 생성자는 접근제어자 + 클래스명과 동일한 매서드명 +() +{} 		
		return sdf1.format(now);
	}

}
