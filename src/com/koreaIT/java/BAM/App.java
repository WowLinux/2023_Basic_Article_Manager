package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.controller.ArticleController;
import com.koreaIT.java.BAM.controller.Controller;
import com.koreaIT.java.BAM.controller.MemberController;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class App {	
	private  List<Article> articles;
	private  List<Member> members;
	
	App() {
		articles  = new ArrayList<>();
		members  = new ArrayList<>();
	} 
	
	public void run() {		
		System.out.println("==프로그램 시작");
		
		makeTestData();
		Scanner sc=new Scanner(System.in);

		MemberController memberController = new MemberController(members,sc);
		ArticleController articleController = new ArticleController(articles, sc);
		
		while(true) {
			System.out.printf("명령어)");
			String cmd = sc.nextLine().trim();
			if(cmd.length() == 0) {
				System.out.println("명령어를 입력해 주세요");
				continue;
			}			
			if(cmd.equals("exit")) {
				break;
			}
			
			String[] cmdBits = cmd.split(" ");//article list
			if(cmdBits.length==1) {
				System.out.println("명령어를 확인해 주세요.");
				continue;
			}
			String controllerName = cmdBits[0];//article
			String methodName = cmdBits[1];  //list
			
			Controller controller = null;
			
			if(controllerName.equals("article")) {
				controller = articleController;				
			} else if(controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("존재 하지 않는 명령어 입니다.");
				continue;
			}
			
			controller.doAction(cmd,methodName);
		}
		
		System.out.println("==프로그램 끝");		
		sc.close();		
	}
	private  void makeTestData() {
		System.out.println("테스트를 위한 게시물 데이터를 생성 합니다.");
		articles.add(new Article(1,Util.getNowDateStr(),"제목1","내용1",11));
		articles.add(new Article(2,Util.getNowDateStr(),"제목2","내용2",22));
		articles.add(new Article(3,Util.getNowDateStr(),"제목3","내용3",33));
	}
}
