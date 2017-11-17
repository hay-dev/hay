package com.depromeet.hay.domain;

import java.util.Date;

public class Comment {
	private int id;
	private int authorId;
	private String content;
	private Date writeDate;
	private int articleId;
	
	private Member member;
	
	public Comment () {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Member getMember() {
		return member;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", authorId=" + authorId + ", content=" + content + ", writeDate=" + writeDate
				+ ", articleId=" + articleId + ", member=" + member + "]";
	}
}
