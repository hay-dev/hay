package com.depromeet.hay.dao;

import com.depromeet.hay.domain.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class CommentDao {

    @PersistenceContext
    private EntityManager entityManager;

    public int addComment(Comment comment) {
        entityManager.persist(comment);
        return comment.getId();
    }
}
