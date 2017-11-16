package com.depromeet.hay.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.hay.domain.Article;

@Repository
@Transactional
public class ArticleDao {
	
	public static final String NAMESPACE = "com.depromeet.hay.mapper.ArticleMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	
	public void add(Article article) {
		sqlSession.insert(NAMESPACE + "add", article);
	}
	
	// 특정 글 하나만 갖고오기 (front로부터 전달받은 id 이용)
	public Article getArticle(int id) {
		return sqlSession.selectOne(NAMESPACE + "getArticle", id);
	}
	
	// 전체 글 목록 가져오기
	public List<Article> getAllArticles() {
		return sqlSession.selectList(NAMESPACE + "getAllArticles");
	}
	
	public void deleteAll() {
		sqlSession.delete(NAMESPACE + "deleteAll");
	}
}
