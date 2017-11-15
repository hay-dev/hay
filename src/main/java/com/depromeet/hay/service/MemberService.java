package com.depromeet.hay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depromeet.hay.dao.MemberDao;
import com.depromeet.hay.domain.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void signUp(Member member) {
		memberDao.add(member);
	}
}
