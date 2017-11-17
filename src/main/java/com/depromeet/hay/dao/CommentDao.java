package com.depromeet.hay.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.hay.domain.Comment;

@Repository
@Transactional
public class CommentDao {
	public static final String NAMESPACE = "com.depromeet.hay.mapper.CommentMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	public void addComment(Comment comment) {
		sqlSession.insert(NAMESPACE + "addComment", comment);
	}
}
