package com.depromeet.hay.service;

import java.util.HashMap;
import java.util.List;

import com.depromeet.hay.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depromeet.hay.dao.ArticleDao;
import com.depromeet.hay.domain.Article;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    public void writeArticle(Article article) {
        articleDao.addArticle(article);
    }

    public Article getArticle(int id) {
        return articleDao.getArticle(id);
    }

    public List<Article> getAllArticles() {
        return articleDao.getAllArticles();
    }

    public Article getRecentArticle() {
        return articleDao.getRecentArticle();
    }

    public List<Article> searchArticles(String text, int beforeAddedThan) {
        return articleDao.findArticles(text, beforeAddedThan);
    }

    public List<Article> searchRecentArticles(String text) {
        return articleDao.findArticles(text);
    }

    public void deleteArticle(int id) {
        articleDao.deleteArticle(id);
    }

    public void modifyArticle(Article article) {
        articleDao.modifyArticle(article);
    }

    public List<Member> getLikers(int id) {
        return articleDao.getArticle(id).getLikers();
    }
}
