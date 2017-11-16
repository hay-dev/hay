package com.depromeet.hay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depromeet.hay.dao.ArticleDao;
import com.depromeet.hay.domain.Article;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleDao articleDao;
	
	public void writeArticle(Article article) {
		this.articleDao.add(article);
	}
	
	public Article getArticle(int id) {
		return this.articleDao.getArticle(id);
	}
	
	// 전체 글 리스트
	public List<Article> getAllArticles() {
		return this.articleDao.getAllArticles(); 
	}

	public void deleteArticle(int id) {
		this.articleDao.deleteArticle(id);
	}
}
