package com.depromeet.hay.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String content;

    @JsonFormat(pattern = "MMM.dd.yyyy", locale = "en")
    @Column(name = "write_date", nullable = false, insertable = false)
    private Date writeDate;

    private int weather;

    @Column(length = 50)
    private String location;

    @Column(name = "like_cnt", nullable = false)
    private int likeCnt;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Member author;

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

    public Member getAuthor() {
        return author;
    }

    public void setAuthor(Member author) {
        this.author = author;
    }
}
