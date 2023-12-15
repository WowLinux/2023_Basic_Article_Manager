package com.koreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class MemberController extends Controller{

	private List<Member> members; 
	private Scanner sc;
	private String cmd;
	private Member loginedMember;
	
	public MemberController(Scanner sc) {
		this.members = new ArrayList<>();
		this.sc = sc;
	
	}
	
	@Override
	public void doAction(String cmd, String methodName) {
		this.cmd = cmd;
		
		switch(methodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		}
	}
	
	private void doLogin() {
		System.out.printf("로그인 아이디 : ");			
		String loginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");			
		String loginPw = sc.nextLine();
		
//		System.out.printf("%s, %s\n", loginId, loginPw);
//      사용자의 입력 아이디와 일치하는 회원이 우리한테 있나?
		Member member = getMemberByLoginId(loginId);
		
		if(member == null) {
			System.out.println("일치하는 회원이 없습니다.");
			return; 
		}
		if(member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번를 확인하세요");
			return;
		}
		loginedMember = member;
		System.out.println("로그인 성공!");
	}

	private Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		
		if(index != -1) {
			return members.get(index);
			
		}
		return null;
	}

	public void doJoin() {
		int id = members.size() + 1; 
		String regDate = Util.getNowDateStr();
		
		String loginId = null;
		while(true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();
			
			if(loginIdChk(loginId) == false ) {
				System.out.printf("%s은(는) 이미 사용중인 아이디 입니다\n",loginId);
				continue;
			}
			System.out.printf("%s은(는) 사용가능한 아이디입니다\n", loginId);
			break;
		}
		String loginPw = null;
		String loginPwChk = null;
		
		while(true) {
			System.out.printf("로그인 비밀번호 : ");			
			loginPw = sc.nextLine();
			System.out.printf("비밀번호 확인: ");			
			loginPwChk = sc.nextLine();
			if(loginPw.equals(loginPwChk) == false) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
			break;
		}
		
		System.out.printf("이름 : ");			
		String name = sc.nextLine();
		
		Member member = new Member(id,regDate, loginId, loginPw, name);
		members.add(member); 
				
		System.out.printf("%s회원님 로그인 되었습니다\n",loginId); 
		
	}

	private boolean loginIdChk(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		
		if(index == -1) {
			
			return true;
		}
		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for(Member member : members) {
			if(member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}
}
