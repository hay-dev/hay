package com.depromeet.hay.service;

import com.depromeet.hay.dao.FollowDao;
import com.depromeet.hay.domain.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depromeet.hay.dao.MemberDao;
import com.depromeet.hay.domain.Member;

import java.util.List;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private FollowDao followDao;
	
	public void signUp(Member member) {
		memberDao.add(member);
	}

	public void follow(Follow follow) {
		followDao.add(follow);
	}

	public List<Member> getFollowers(int id) {
		return memberDao.getFollowers(id);
	}
}
