package com.depromeet.hay.domain;

import java.util.Date;

public class Article {
	
	private int id;
	private String title;
	private String content;
	private Date writeDate;
	private int weather;
	private String location;
	private int like_cnt;
	private int author;
	
	@Override
	public boolean equals(Object obj) {
		Article target = (Article) obj;
		return title != null && content != null && location != null &&
				target.getTitle() != null && target.getContent() != null && target.getLocation() != null &&
				title.equals(target.getTitle()) && content.equals(target.getContent()) &&
				weather == target.getWeather() && location.equals(target.getLocation());
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
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
	
	public int getWeather() {
		return weather;
	}
	
	public void setWeather(int weather) {
		this.weather = weather;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getLike_cnt() {
		return like_cnt;
	}
	
	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}
	
	public int getAuthor() {
		return author;
	}
	
	public void setAuthor(int author) {
		this.author = author;
	}
}
