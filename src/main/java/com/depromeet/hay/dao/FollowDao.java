package com.depromeet.hay.dao;

import com.depromeet.hay.domain.Follow;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class FollowDao {

    public static final String NAMESPACE = "com.depromeet.hay.mapper.FollowMapper.";

    @Autowired
    private SqlSession sqlSession;

    public void add(Follow follow) {
        sqlSession.insert(NAMESPACE + "add", follow);
    }
}
