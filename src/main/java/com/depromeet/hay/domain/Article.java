package com.depromeet.hay.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(name = "write_date", nullable = false)
    private Date writeDate;

    private int weather;

    @Column(length = 50)
    private String location;

    @Column(name = "like_cnt", nullable = false)
    private int likeCnt;

    @Column(nullable = false)
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

    public int getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }
}
