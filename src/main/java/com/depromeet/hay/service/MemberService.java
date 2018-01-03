package com.depromeet.hay.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.hay.dao.ArticleDao;
//import com.depromeet.hay.dao.FollowDao;
import com.depromeet.hay.dao.MemberDao;
import com.depromeet.hay.domain.Article;
import com.depromeet.hay.domain.Member;

@Service
@Transactional
public class MemberService implements UserDetailsService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private ArticleDao articleDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberDao.getMember(email);
		
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		return new User(String.valueOf(member.getId()), member.getPassword(), authorities);
	}
	
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
		
		follower.setFollowerCnt(follower.getFollowerCnt() + 1);
		following.setFollowingCnt(following.getFollowingCnt() + 1);
	}

	public void unfollow(int followerId, int followingId) {
		Member follower = memberDao.getMember(followerId);
		Member following = memberDao.getMember(followingId);
		follower.removeFollowing(following);
		follower.addFollowing(following);
		
		follower.setFollowerCnt(follower.getFollowerCnt() - 1);
		following.setFollowingCnt(following.getFollowingCnt() - 1);
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
		member.addLiking(article);
	}
	
	public void unlike(int memberId, int articleId) {
		Member member = memberDao.getMember(memberId);
		Article article = articleDao.getArticle(articleId);
		member.removeLiking(article);
	}

	public List<Article> getLikings(int id) {
		return memberDao.getMember(id).getLikings();
	}
}
