package com.depromeet.hay.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.hay.domain.Member;

@Repository
@Transactional
public class MemberDao {
	
	public static final String NAMESPACE = "com.depromeet.hay.mapper.MemberMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	
	public int add(Member member) {
		return sqlSession.insert(NAMESPACE + "add", member);
	}
	
	public Member get(int id) {
		return sqlSession.selectOne(NAMESPACE + "getById", id);
	}
	
	public Member get(String email) {
		return sqlSession.selectOne(NAMESPACE + "getByEmail", email);
	}
	
	public void deleteAll() {
		sqlSession.delete(NAMESPACE + "deleteAll");
	}
}
