package com.depromeet.hay.dto;

import java.util.Date;

public class Member {
	
	private String id;
	private String email;
	private String password;
	private String name;
	private Date birth;
	private String phone;
	private int followingCnt;
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
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
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
