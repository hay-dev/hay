package com.depromeet.hay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.depromeet.hay.dao.FollowDao;
import com.depromeet.hay.dao.MemberDao;
import com.depromeet.hay.domain.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
//	@Autowired
//	private FollowDao followDao;
	
	public void signUp(Member member) {
		memberDao.addMember(member);
	}

	public List<Member> search(String text) {
		return memberDao.findMembers(text);
	}

	public void follow(int followerId, int followingId) {
		Member follower = memberDao.getMember(followerId);
		Member following = memberDao.getMember(followingId);
		follower.addFollowing(following);
	}

//	public List<Member> getFollowers(int id) {
//		return memberDao.getFollowers(id);
//	}
//
//	public List<Member> getFollowings(int id) {
//		return memberDao.getFollowings(id);
//	}

	public void modifyMember(Member member) {
		this.memberDao.modifyMember(member);
	}
}
