package com.depromeet.hay.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.hay.dto.Member;

@Component
@Transactional
public class MemberDao {
	
	public static final String NAMESPACE = "com.depromeet.hay.mapper.MemberMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int add(Member member) {
		return sqlSession.insert(NAMESPACE + "add", member);
	}
	
	public Member get(String email) {
		return sqlSession.selectOne(NAMESPACE + "get", email);
	}
	
	public void deleteAll() {
		sqlSession.delete(NAMESPACE + "deleteAll");
	}
}
