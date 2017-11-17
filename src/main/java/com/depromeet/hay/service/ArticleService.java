package com.depromeet.hay.service;

import java.util.HashMap;
import java.util.List;

import com.depromeet.hay.dao.MemberDao;
import com.depromeet.hay.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depromeet.hay.dao.ArticleDao;
import com.depromeet.hay.domain.Article;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private MemberDao memberDao;
	
	public void writeArticle(Article article) {
		this.articleDao.add(article);
	}
	
	public Article getArticle(int id) {
		Article article = this.articleDao.getArticle(id);
		Member member = this.memberDao.get(article.getAuthor());
		article.setMember(member);

		return article;
	}
	
	// 전체 글 리스트
	public List<Article> getAllArticles() {
		return this.articleDao.getAllArticles(); 
	}

	public void deleteArticle(int id) {
		this.articleDao.deleteArticle(id);
	}

	public void modifyArticle(Article article) {
		this.articleDao.modifyArticle(article);
	}
}
