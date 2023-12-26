package com.koreaIT.java.BAM.service;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dao.MemberDao;
import com.koreaIT.java.BAM.dto.Member;

public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = Container.memberDao;		
	}  


	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public boolean loginIdChk(String loginId) {
		return memberDao.loginIdChk(loginId);		
	}

	public void add(Member member) {
		memberDao.add(member);
	}

	public int setArticleId() {
		return memberDao.setArticleId();
	}

	public String getWriterName(int memberId) {
		return memberDao.getWriterName(memberId);
	}
}
