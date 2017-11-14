package com.depromeet.hay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depromeet.hay.dao.ArticleDao;
import com.depromeet.hay.domain.Article;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleDao articleDao;
	
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	public void writeArticle(Article article) {
		articleDao.add(article);
	}
}
