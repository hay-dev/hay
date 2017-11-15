package com.depromeet.hay.dao;

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
	
	public Article get(int id) {
		return sqlSession.selectOne(NAMESPACE + "get", id);
	}
	
	public void deleteAll() {
		sqlSession.delete(NAMESPACE + "deleteAll");
	}
}
