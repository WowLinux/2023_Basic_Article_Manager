package com.koreaIT.java.BAM;

import java.util.Scanner;

import com.koreaIT.java.BAM.controller.ArticleController;
import com.koreaIT.java.BAM.controller.Controller;
import com.koreaIT.java.BAM.controller.MemberController;

public class App {	

	
//	App() {
//
//	} 
 
	public void run() {		
		System.out.println("==프로그램 시작");
		
		Scanner sc=new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		articleController.makeTestData();
		memberController.makeTestData();
		
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
			
			Controller controller = null; //Controller타입으로 만든 변수 controller
			
			if(controllerName.equals("article")) {
				controller = articleController;				
			} else if(controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("존재 하지 않는 명령어 입니다.");
				continue;
			}
//			String  actionName = controllerName + "/" + methodName;
			
			switch(methodName) {
			case "write":
			case "modify":
			case "delete":
			case "logout":
			case "profile":
				if(Controller.isLogined() == false) {
					System.out.println("로그인 후 이용 가능 합니다.");
					continue;
				}				
				break;
			case "login":
			case "join":
				if(Controller.isLogined()) {
					System.out.println("로그아웃 후 이용해 주세요.");
					continue;
				}
				break;
			}
			controller.doAction(cmd,methodName);
		}
		
		System.out.println("==프로그램 끝");		
		sc.close();		
	}

}
