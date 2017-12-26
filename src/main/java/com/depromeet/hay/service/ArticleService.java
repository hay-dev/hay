package com.depromeet.hay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.hay.dao.ArticleDao;
import com.depromeet.hay.domain.Article;
import com.depromeet.hay.domain.Comment;
import com.depromeet.hay.domain.Member;

@Service
@Transactional
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

    public void writeComment(int id, Comment comment) {
        Article article = articleDao.getArticle(id);
        article.addComment(comment);
    }

    public List<Comment> getComments(int id) {
        return articleDao.getArticle(id).getComments();
    }
}
