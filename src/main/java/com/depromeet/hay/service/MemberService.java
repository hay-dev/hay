package com.depromeet.hay.service;

import java.util.List;

import com.depromeet.hay.dao.ArticleDao;
import com.depromeet.hay.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.depromeet.hay.dao.FollowDao;
import com.depromeet.hay.dao.MemberDao;
import com.depromeet.hay.domain.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private ArticleDao articleDao;
	
	public void signUp(Member member) {
		memberDao.addMember(member);
	}

	public List<Member> search(String text) {
		return memberDao.findMembers(text);
	}

	public void modifyMember(Member member) {
		this.memberDao.modifyMember(member);
	}

	public void follow(int followerId, int followingId) {
		Member follower = memberDao.getMember(followerId);
		Member following = memberDao.getMember(followingId);
		follower.addFollowing(following);
	}

	public List<Member> getFollowers(int id) {
		return memberDao.getMember(id).getFollowers();
	}

	public List<Member> getFollowings(int id) {
		return memberDao.getMember(id).getFollowings();
	}

	public void like(int memberId, int articleId) {
		Member member = memberDao.getMember(memberId);
		Article article = articleDao.getArticle(articleId);
		member.addLikings(article);
	}

	public List<Article> getLikings(int id) {
		return memberDao.getMember(id).getLikings();
	}
}
