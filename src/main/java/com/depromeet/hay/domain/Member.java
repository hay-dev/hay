package com.depromeet.hay.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "member")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private int id;

	@Column(nullable = false, length = 50)
	private String email;

	@Transient
	@Column(nullable = false, length = 20)
	private String password;

	@Column(length = 10)
	private String name;

	private Date birth;

	@Column(length = 12)
	private String phone;

	@ManyToMany
	@JoinTable(
			name = "follow",
			joinColumns = @JoinColumn(name = "following_id"),
			inverseJoinColumns = @JoinColumn(name = "follower_id")
	)
	private List<Member> followings = new ArrayList<>();

	@ManyToMany
	@JoinTable(
			name = "follow",
			joinColumns = @JoinColumn(name = "follower_id"),
			inverseJoinColumns = @JoinColumn(name = "following_id")
	)
	private List<Member> followers = new ArrayList<>();

	@ManyToMany
	@JoinTable(
			name = "like",
			joinColumns = @JoinColumn(name = "member_id"),
			inverseJoinColumns = @JoinColumn(name = "article_id")
	)
	private List<Article> likings = new ArrayList<>();

	@Column(name = "following_cnt", nullable = false)
	private int followingCnt;

	@Column(name = "follower_cnt", nullable = false)
	private int followerCnt;
	
	public Member() {
		
	}
	
	public Member(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	@Override
	public boolean equals(Object obj) {
		Member target = (Member) obj;
		return this.email != null && target.getEmail() != null &&
				this.password != null && target.getPassword() != null &&
				this.email.equals(target.getEmail()) && this.password.equals(target.getPassword());
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getBirth() {
		return birth;
	}
	
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Member> getFollowings() {
		return followings;
	}

	public void setFollowings(List<Member> followings) {
		this.followings = followings;
	}

	public void addFollowing(Member following) {
		this.followings.add(following);
	}
	
	public void removeFollowing(Member following) {
		this.followings.remove(following);
	}

	public List<Member> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Member> followers) {
		this.followers = followers;
	}

	public void addFollower(Member follower) {
		this.followers.add(follower);
	}
	
	public void removeFollower(Member follower) {
		this.followers.remove(follower);
	}

	public List<Article> getLikings() {
		return likings;
	}

	public void setLikings(List<Article> likings) {
		this.likings = likings;
	}

	public void addLiking(Article liking) {
		this.likings.add(liking);
	}
	
	public void removeLiking(Article liking) {
		this.likings.remove(liking);
	}

	public int getFollowingCnt() {
		return followingCnt;
	}
	
	public void setFollowingCnt(int followingCnt) {
		this.followingCnt = followingCnt;
	}
	
	public int getFollowerCnt() {
		return followerCnt;
	}
	
	public void setFollowerCnt(int followerCnt) {
		this.followerCnt = followerCnt;
	}
}
