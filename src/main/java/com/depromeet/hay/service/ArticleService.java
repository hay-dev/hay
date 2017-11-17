package com.depromeet.hay.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depromeet.hay.dao.ArticleDao;
import com.depromeet.hay.dao.CommentDao;
import com.depromeet.hay.dao.MemberDao;
import com.depromeet.hay.domain.Article;
import com.depromeet.hay.domain.Comment;
import com.depromeet.hay.domain.Member;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private CommentDao commentDao;
	
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

	public void modifyArticle(Article article) {
		this.articleDao.modifyArticle(article);
	}

	public void addComment(int articleId, int authorId, Comment comment) {
		Article article = this.articleDao.getArticle(articleId);
		Member member = this.memberDao.get(authorId);
		comment.setArticleId(article.getId());
		comment.setAuthorId(member.getId());
		
		this.commentDao.addComment(comment);
	}
}
